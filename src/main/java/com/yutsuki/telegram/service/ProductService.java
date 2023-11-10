package com.yutsuki.telegram.service;

import com.yutsuki.telegram.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductService {
    @Resource
    private ProductRepository productRepository;

    public ResponseEntity<?> createProduct(){

        return null;
    }

}
