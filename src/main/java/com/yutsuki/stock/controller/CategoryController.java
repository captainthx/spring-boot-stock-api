package com.yutsuki.stock.controller;

import com.yutsuki.stock.model.request.CategoryCreateRequest;
import com.yutsuki.stock.model.request.DeleteCategoryRequest;
import com.yutsuki.stock.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;


    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryCreateRequest request, Authentication authentication) {
        return categoryService.createCategory(request, authentication);
    }

    @GetMapping
    public ResponseEntity<?> findCategoryList() {
        return categoryService.findCategoryList();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCategory(DeleteCategoryRequest request, Authentication authentication) {
        return categoryService.deleteCategory(request, authentication);
    }
}
