package com.yutsuki.telegram.service;

import com.yutsuki.telegram.com.OperateLogsType;
import com.yutsuki.telegram.com.Pagination;
import com.yutsuki.telegram.entity.*;
import com.yutsuki.telegram.model.request.ProductCreateRequest;
import com.yutsuki.telegram.model.request.UpdStockProductRequest;
import com.yutsuki.telegram.model.response.ProductCreateResponse;
import com.yutsuki.telegram.repository.AccountRepository;
import com.yutsuki.telegram.repository.CategoryRepository;
import com.yutsuki.telegram.repository.ProductRepository;
import com.yutsuki.telegram.repository.StockRepository;
import com.yutsuki.telegram.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static com.yutsuki.telegram.exception.HandleException.exception;

@Service
@Slf4j
public class ProductService {
    @Resource
    private ProductRepository productRepository;
    @Resource
    private CategoryRepository categoryRepository;
    @Resource
    private StockRepository stockRepository;
    @Resource
    private TelegramService telegramService;
    @Resource
    private AccountRepository accountRepository;

    public ResponseEntity<?> createProduct(ProductCreateRequest request) {
        if (request.getProductName().isEmpty()) {
            log.info("Create Product::(block). [invalid product name]. req: {}", request);
            return ResponseEntity.badRequest().body("invalid product name");
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

        Optional<Category> optionalCategory = this.categoryRepository.findById(request.getCategoryId());
        if (!optionalCategory.isPresent()) {
            log.info("Create Product::(block). [category not found]. req: {}", request);
            return ResponseEntity.badRequest().body("category not found");
        }
        Optional<Stock> optionalStock = this.stockRepository.findById(request.getStockId());
        if (!optionalStock.isPresent()) {
            log.info("Create Product::(block). [stock not found]. req: {}", request);
            return ResponseEntity.badRequest().body("stock not found");
        }

        // set to entity
        Product entity = new Product();
        entity.setProductName(request.getProductName());
        entity.setCategoryId(request.getCategoryId());
        entity.setStockQuantity(request.getStockQuantity());
        entity.setPrice(request.getPrice());
        entity.setCost(request.getCost());
        entity.setStockId(request.getStockId());
        return ResponseEntity.ok().body(this.createResponse(this.productRepository.save(entity)));
    }


    public ResponseEntity<?> findAllProduct(Pagination pagination) {
        Page<ProductCreateResponse> productList = this.productRepository.findAll(pagination).map(this::createResponse);
        return ResponseEntity.ok(productList);
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<?> updateProduct(Authentication authentication, UpdStockProductRequest request) {
        Jwt credentials = (Jwt) authentication.getCredentials();
        Long uid = (Long) credentials.getClaims().get("id");
        Account account = this.accountRepository.findById(uid).get();
        Optional<Product> productOptional = this.productRepository.findById(request.getProductId());
        if (!productOptional.isPresent()) {
            log.warn("Update Stock Product::(block). [product not found]. req: {}", request);
            return exception("product not found");
        }
        Product product = productOptional.get();
        // copy Properties
        Product before = new Product();
        BeanUtils.copyProperties(product, before);

        if (Objects.nonNull(request.getCategoryId())) {
            product.setCategoryId(request.getCategoryId());
        }
        if (Objects.nonNull(request.getStockId())) {
            product.setStockId(request.getStockId());
        }
        if (Objects.nonNull(request.getCost()) && request.getCost() > 0) {
            product.setCost(request.getCost());
        }
        if (Objects.nonNull(request.getPrice()) && request.getPrice() > 0) {
            product.setPrice(request.getPrice());
        }
        product.setStockQuantity(request.getStockQuantity());
        Product res = this.productRepository.save(product);
        AdminLogs logs = new AdminLogs();
        logs.setUid(uid);
        logs.setPrevious(JsonUtils.toJsonIfNotNull(before));
        logs.setAfter(JsonUtils.toJsonIfNotNull(res));
        logs.setAtTime(LocalDateTime.now());
        logs.setType(OperateLogsType.UPDATE_PRODUCT.getMapping());

        String msg = String.format("Update Product::(success). [name: %s] [req: %s]", account.getUsername(), request);
        this.telegramService.sendMessage(msg);

        return ResponseEntity.ok().build();
    }


    private ProductCreateResponse createResponse(Product product) {
        return ProductCreateResponse.builder()
                .id(product.getId())
                .categoryId(product.getCategoryId())
                .productName(product.getProductName())
                .stockQuantity(product.getStockQuantity())
                .price(product.getPrice())
                .cost(product.getCost())
                .build();
    }
}
