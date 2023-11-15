package com.yutsuki.telegram.controller;

import com.yutsuki.telegram.com.Pagination;
import com.yutsuki.telegram.model.request.DeleteProductRequest;
import com.yutsuki.telegram.model.request.ProductCreateRequest;
import com.yutsuki.telegram.model.request.UpdStockProductRequest;
import com.yutsuki.telegram.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/product")
public class ProductController {
    @Resource
    private ProductService productService;


    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductCreateRequest request, Authentication authentication) {
        return this.productService.createProduct(request, authentication);
    }

    @GetMapping
    public ResponseEntity<?> findAllProduct(Pagination pagination) {
        return this.productService.findAllProduct(pagination);
    }

    @PatchMapping
    public ResponseEntity<?> updateProduct(@RequestBody UpdStockProductRequest request, Authentication authentication) {
        return this.productService.updateProduct(authentication, request);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(DeleteProductRequest request, Authentication authentication) {
        return this.productService.deleteProduct(request, authentication);
    }
}
