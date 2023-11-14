package com.yutsuki.telegram.controller;

import com.yutsuki.telegram.model.request.CategoryCreateRequest;
import com.yutsuki.telegram.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;


    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryCreateRequest request) {
        return categoryService.createCategory(request);
    }
    @GetMapping
    public ResponseEntity<?> findCategoryList() {
        return categoryService.findCategoryList();
    }
}
