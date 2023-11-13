package com.yutsuki.telegram.service;

import com.yutsuki.telegram.com.Pagination;
import com.yutsuki.telegram.entity.Category;
import com.yutsuki.telegram.entity.Product;
import com.yutsuki.telegram.model.request.ProductCreateRequest;
import com.yutsuki.telegram.model.response.ProductCreateResponse;
import com.yutsuki.telegram.repository.CategoryRepository;
import com.yutsuki.telegram.repository.ProductRepository;
import com.yutsuki.telegram.utils.MapperUtils;
import com.yutsuki.telegram.utils.ValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.ws.Response;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {
    @Resource
    private ProductRepository productRepository;
    @Resource
    private CategoryRepository categoryRepository;

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
        // set to entity
        Product entity = new Product();
        entity.setProductName(request.getProductName());
        entity.setCategoryId(request.getCategoryId());
        entity.setStockQuantity(request.getStockQuantity());
        entity.setPrice(request.getPrice());
        entity.setCost(request.getCost());

        return ResponseEntity.ok().body(this.createResponse(this.productRepository.save(entity)));
    }


    public ResponseEntity<?> findAllProduct(Pagination pagination) {
        Page<ProductCreateResponse> productList = this.productRepository.findAll(pagination).map(this::createResponse);
        return ResponseEntity.ok(productList);
    }



    private ProductCreateResponse createResponse(Product product) {
        return ProductCreateResponse.builder()
                .id(product.getId())
                .categoryId(product.getCategoryId())
                .productName(product.getProductName())
                .stockQuantity(product.getStockQuantity())
                .price(product.getPrice())
                .build();
    }
}
