package com.yutsuki.stock.service;

import com.yutsuki.stock.com.OperateLogsType;
import com.yutsuki.stock.com.Pagination;
import com.yutsuki.stock.entity.*;
import com.yutsuki.stock.model.request.DeleteProductRequest;
import com.yutsuki.stock.model.request.NotificationsRequest;
import com.yutsuki.stock.model.request.CreateProductRequest;
import com.yutsuki.stock.model.request.UpdStockProductRequest;
import com.yutsuki.stock.model.response.CreateProductResponse;
import com.yutsuki.stock.repository.*;
import com.yutsuki.stock.utils.Comm;
import com.yutsuki.stock.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static com.yutsuki.stock.com.HandleResponse.success;
import static com.yutsuki.stock.com.HandleResponse.successList;
import static com.yutsuki.stock.exception.HandleException.exception;

@Service
@Slf4j
public class ProductService {
    @Resource
    private ProductRepository productRepository;
    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private TelegramService telegramService;
    @Resource
    private AccountRepository accountRepository;
    @Resource
    private AdminLogsRepository adminLogsRepository;
    @Resource
    private NotificationService notificationService;

    public ResponseEntity<?> createProduct(CreateProductRequest request, Authentication authentication) {
        Long uid = Comm.getUid(authentication);
        St_account account = this.accountRepository.findById(uid).get();
        if (request.getProductName().isEmpty()) {
            log.info("Create Product::(block). [invalid product name]. req: {}", request);
            return ResponseEntity.badRequest().body("invalid product name");
        }
        if (Objects.isNull(request.getProductImage())){
            log.info("Create Product::(block). [invalid product image]. req: {}", request);
            return ResponseEntity.badRequest().body("invalid product image");
        }
        if (Objects.isNull(request.getPrice())) {
            log.info("Create Product::(block). [invalid price]. req: {}", request);
            return ResponseEntity.badRequest().body("invalid price");
        }
        if (Objects.isNull(request.getStockQuantity())) {
            log.info("Create Product::(block). [invalid stock quantity]. req: {}", request);
            return ResponseEntity.badRequest().body("invalid stock quantity");
        }
        if (Objects.isNull(request.getCategoryId())) {
            log.info("Create Product::(block). [invalid category id]. req: {}", request);
            return ResponseEntity.badRequest().body("invalid category id");
        }
        if (Objects.isNull(request.getCost())) {
            log.info("Create Product::(block). [invalid cost]. req: {}", request);
            return ResponseEntity.badRequest().body("invalid cost");
        }

        Optional<St_category> optionalCategory = this.categoryRepository.findById(request.getCategoryId());
        if (!optionalCategory.isPresent()) {
            log.info("Create Product::(block). [category not found]. req: {}", request);
            return ResponseEntity.badRequest().body("category not found");
        }

        // set to entity
        St_Product entity = new St_Product();
        entity.setProductName(request.getProductName());
        entity.setProductImage(request.getProductImage());
        entity.setCategoryId(request.getCategoryId());
        entity.setStockQuantity(request.getStockQuantity());
        entity.setPrice(request.getPrice());
        entity.setCost(request.getCost());

        String msg = String.format("Create Product By name: %s productName: %s detail: %s", account.getUsername(), entity.getProductName(), request);
        this.notificationService.sendNotification(NotificationsRequest.builder().notifications(msg).build());
        return ResponseEntity.ok(this.createResponse(this.productRepository.save(entity)));
    }


    public ResponseEntity<?> findAllProduct(Long categoryId,Pagination pagination) {
        Page<St_Product> productList;
        if (categoryId != null) {
            productList = this.productRepository.findByCategoryId(categoryId, pagination);
        } else {
            productList = this.productRepository.findAll(pagination);
        }
        return successList(productList);
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<?> updateProduct(Authentication authentication, UpdStockProductRequest request) {
        Long uid = Comm.getUid(authentication);
        St_account account = this.accountRepository.findById(uid).get();
        Optional<St_Product> productOptional = this.productRepository.findById(request.getProductId());
        if (!productOptional.isPresent()) {
            log.warn("Update Stock Product::(block). [product not found]. req: {}", request);
            return exception("product not found");
        }
        St_Product product = productOptional.get();
        // copy Properties
        St_Product before = new St_Product();
        BeanUtils.copyProperties(product, before);

      if (!Objects.isNull(request.getPrice()) && !product.getPrice().equals(request.getPrice())){
            product.setPrice(request.getPrice());
      }
      if (!Objects.isNull(request.getCost()) &&!product.getCost().equals(request.getCost())){
          product.setCost(request.getCost());
      }

        product.setStockQuantity(product.getStockQuantity() + request.getStockQuantity());
        St_Product res = this.productRepository.save(product);

        St_adminLogs logs = new St_adminLogs();
        logs.setUid(uid);
        logs.setPrevious(JsonUtils.toJsonIfNotNull(before));
        logs.setAfter(JsonUtils.toJsonIfNotNull(res));
        logs.setAtTime(LocalDateTime.now());
        logs.setType(OperateLogsType.UPDATE_PRODUCT.getMapping());
        this.adminLogsRepository.save(logs);


        String msg = String.format("Update Product By name: %s productName: %s detail: %s", account.getUsername(), product.getProductName(), request);
        this.telegramService.sendMessage(msg);
        this.notificationService.sendNotification(NotificationsRequest.builder().notifications(msg).build());
        return ResponseEntity.ok(res);
    }

    public ResponseEntity<?> deleteProduct(DeleteProductRequest request, Authentication authentication) {
        Long uid = Comm.getUid(authentication);
        St_account account = this.accountRepository.findById(uid).get();
        Optional<St_Product> productOptional = this.productRepository.findById(request.getProductId());
        if (!productOptional.isPresent()) {
            log.warn("Delete Product::(block). [product not found]. productId: {}", request.getProductId());
            return exception("product not found");
        }
        St_Product product = productOptional.get();
        this.productRepository.deleteById(request.getProductId());
        String msg = String.format("Delete Product By name: %s productName: %s ", account.getUsername(), product.getProductName());
        this.notificationService.sendNotification(NotificationsRequest.builder().notifications(msg).build());
        return success();
    }

    private CreateProductResponse createResponse(St_Product product) {
        return CreateProductResponse.builder()
                .productId(product.getId())
                .categoryId(product.getCategoryId())
                .productName(product.getProductName())
                .stockQuantity(product.getStockQuantity())
                .price(product.getPrice())
                .cost(product.getCost())
                .build();
    }
}
