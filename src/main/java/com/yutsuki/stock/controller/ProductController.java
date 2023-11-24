package com.yutsuki.stock.controller;

import com.yutsuki.stock.com.Pagination;
import com.yutsuki.stock.model.request.DeleteProductRequest;
import com.yutsuki.stock.model.request.CreateProductRequest;
import com.yutsuki.stock.model.request.UpdStockProductRequest;
import com.yutsuki.stock.service.ProductService;
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
    public ResponseEntity<?> createProduct(@Valid @RequestBody CreateProductRequest request, Authentication authentication) {
        return this.productService.createProduct(request, authentication);
    }

    @GetMapping
    public ResponseEntity<?> findAllProduct(Long categoryId,Pagination pagination) {
        return this.productService.findAllProduct(categoryId,pagination);
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
