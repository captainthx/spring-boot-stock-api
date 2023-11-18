package com.yutsuki.stock.service;

import com.yutsuki.stock.com.OperateLogsType;
import com.yutsuki.stock.com.Pagination;
import com.yutsuki.stock.entity.St_Product;
import com.yutsuki.stock.entity.St_account;
import com.yutsuki.stock.entity.St_adminLogs;
import com.yutsuki.stock.entity.St_orders;
import com.yutsuki.stock.model.request.CreateOrdersRequest;
import com.yutsuki.stock.model.request.NotificationsRequest;
import com.yutsuki.stock.model.response.CreateOrdersResponse;
import com.yutsuki.stock.repository.AccountRepository;
import com.yutsuki.stock.repository.AdminLogsRepository;
import com.yutsuki.stock.repository.OrdersRepository;
import com.yutsuki.stock.repository.ProductRepository;
import com.yutsuki.stock.utils.Comm;
import com.yutsuki.stock.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.yutsuki.stock.com.HandleResponse.successList;
import static com.yutsuki.stock.exception.HandleException.exception;

@Service
@Slf4j
public class OrdersService {
    @Resource
    private OrdersRepository ordersRepository;
    @Resource
    private AccountRepository accountRepository;
    @Resource
    private ProductRepository productRepository;
    @Resource
    private NotificationService notificationService;
    @Resource
    private TelegramService telegramService;
    @Resource
    private AdminLogsRepository adminLogsRepository;

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<?> createOrders(CreateOrdersRequest request, Authentication authentication) {
        Long uid = Comm.getUid(authentication);
        St_account account = this.accountRepository.findById(uid).get();
        Optional<St_Product> productOptional = this.productRepository.findById(request.getProductId());
        if (!productOptional.isPresent()) {
            log.warn("Create Orders::(block). product not found. req: {}", request);
            return exception("product not found");
        }
        St_Product product = productOptional.get();
        if (product.getStockQuantity() == 0) {
            log.warn("Create Orders::(block). stock is empty. req: {}", request);
            return exception("stock is empty");
        }
        if (product.getStockQuantity() < request.getAmount()) {
            log.warn("Create Orders::(block). stock not enough. req: {}", request);
            return exception("stock not enough");
        }
        //update stock
        product.setStockQuantity(product.getStockQuantity() - request.getAmount());
        this.productRepository.save(product);

        // create order
        St_orders entity = new St_orders();
        entity.setProductId(request.getProductId());
        entity.setTotalPrice(request.getAmount() * product.getPrice());
        entity.setTotalQuantity(request.getAmount());
        entity.setCostPerProduct(product.getCost());
        entity.setPricePerProduct(product.getPrice());
        St_orders orderRes = this.ordersRepository.save(entity);

        // create log
        St_adminLogs logs = new St_adminLogs();
        logs.setType(OperateLogsType.CREATE_ORDERS.getMapping());
        logs.setAfter(JsonUtils.toJsonIfNotNull(orderRes));
        logs.setUid(account.getId());
        logs.setAtTime(LocalDateTime.now());
        this.adminLogsRepository.save(logs);

        // notification
        String msg = String.format("Create order By name: %s Product: %s, Amount: %d", account.getUsername(), product.getProductName(), request.getAmount());
        this.notificationService.sendNotification(NotificationsRequest.builder().notifications(msg).build());
        this.telegramService.sendMessage(msg);
        return ResponseEntity.ok().body(this.createOrdersResponse(orderRes, product));
    }

    public ResponseEntity<?> findAllOrders(Pagination pagination) {
        Page<St_orders> orders = this.ordersRepository.findAll(pagination);
        return successList(orders);
    }


    private CreateOrdersResponse createOrdersResponse(St_orders orders, St_Product product) {
        return CreateOrdersResponse.builder()
                .productId(orders.getProductId())
                .productName(product.getProductName())
                .totalPrice(orders.getTotalPrice())
                .costPerProduct(orders.getCostPerProduct())
                .pricePerProduct(orders.getPricePerProduct())
                .totalQuantity(orders.getTotalQuantity())
                .build();
    }


}
