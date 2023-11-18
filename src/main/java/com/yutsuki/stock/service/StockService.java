package com.yutsuki.stock.service;

import com.yutsuki.stock.com.Pagination;
import com.yutsuki.stock.entity.St_account;
import com.yutsuki.stock.entity.St_stock;
import com.yutsuki.stock.model.StockDetail;
import com.yutsuki.stock.model.request.CreateStockRequest;
import com.yutsuki.stock.model.request.DeleteStockRequest;
import com.yutsuki.stock.model.request.NotificationsRequest;
import com.yutsuki.stock.model.response.StockListResponse;
import com.yutsuki.stock.repository.AccountRepository;
import com.yutsuki.stock.repository.ProductRepository;
import com.yutsuki.stock.repository.StockRepository;
import com.yutsuki.stock.utils.Comm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.yutsuki.stock.com.HandleResponse.success;
import static com.yutsuki.stock.exception.HandleException.exception;

@Service
@Slf4j
public class StockService {

    @Resource
    private StockRepository stockRepository;
    @Resource
    private ProductRepository productRepository;
    @Resource
    private NotificationService notificationService;
    @Resource
    private AccountRepository accountRepository;


    public ResponseEntity<?> createStock(CreateStockRequest request, Authentication authentication) {
        Long uid = Comm.getUid(authentication);
        St_account account = this.accountRepository.findById(uid).get();
        if (!StringUtils.hasText(request.getStockName())) {
            log.warn("Stock::(block). Stock name is empty req. {}", request);
            return exception("Stock name is empty");
        }
        St_stock entity = new St_stock();
        entity.setStockName(request.getStockName());
        St_stock res = this.stockRepository.save(entity);
        String msg = String.format("Stock created By name:%s StockName: %s ", account.getUsername(), res.getStockName());
        this.notificationService.sendNotification(NotificationsRequest.builder().notifications(msg).build());
        return ResponseEntity.ok(res);
    }

    public ResponseEntity<?> findStockList(Pagination pagination) {
        List<St_stock> stockListAll = this.stockRepository.findAll().stream().map(e -> {
            St_stock stock = new St_stock();
            stock.setId(e.getId());
            stock.setStockName(e.getStockName());
            return stock;
        }).collect(Collectors.toList());
        Map<Long, St_stock> stockMap = stockListAll.stream().collect(Collectors.toMap(St_stock::getId, Function.identity()));

        List<StockListResponse> responseList = this.productRepository.findAll(pagination).stream()
                .collect(Collectors.groupingBy(
                        e -> stockMap.get(e.getStockId()).getStockName(),
                        Collectors.mapping(e -> {
                            StockDetail stockDetail = new StockDetail();
                            stockDetail.setStockQuantity(e.getStockQuantity());
                            stockDetail.setProductName(e.getProductName());
                            stockDetail.setProductId(e.getId());
                            return stockDetail;
                        }, Collectors.toList())
                ))
                .entrySet().stream()
                .map(entry -> {
                    StockListResponse stockListResponse = new StockListResponse();
                    stockListResponse.setStockName(entry.getKey());
                    stockListResponse.setProductDetails(entry.getValue());
                    stockListResponse.setTotalStockQuantity(entry.getValue().stream().mapToInt(StockDetail::getStockQuantity).sum());
                    stockListResponse.setTotalProduct(entry.getValue().size());
                    return stockListResponse;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    public ResponseEntity<?> deleteStock(DeleteStockRequest request, Authentication authentication) {
        Long uid = Comm.getUid(authentication);
        St_account account = this.accountRepository.findById(uid).get();
        Optional<St_stock> stockOptional = this.stockRepository.findById(request.getStockId());
        if (!stockOptional.isPresent()) {
            log.warn("Stock::(block). Invalid StockId. stockId: {}", request.getStockId());
            return exception("Stock not found");
        }
        St_stock stock = stockOptional.get();
        this.stockRepository.deleteById(request.getStockId());
        String msg = String.format("Stock deleted By name:%s StockName: %s ", account.getUsername(), stock.getStockName());
        this.notificationService.sendNotification(NotificationsRequest.builder().notifications(msg).build());
        return success();
    }

}