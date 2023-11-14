package com.yutsuki.telegram.service;

import com.yutsuki.telegram.entity.Category;
import com.yutsuki.telegram.model.request.CategoryCreateRequest;
import com.yutsuki.telegram.repository.CategoryRepository;
import com.yutsuki.telegram.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class CategoryService {
    @Resource
    private CategoryRepository categoryRepository;


    public ResponseEntity<?> createCategory(CategoryCreateRequest request) {
        if (request.getCategoryName().isEmpty()) {
            log.warn("Category::(block). [invalid category name]. req: {}", request);
        }
        Category entity = new Category();
        entity.setCategoryName(request.getCategoryName());
        Category res = categoryRepository.save(entity);
        return ResponseEntity.ok().body(res);
    }

    public ResponseEntity<?> findCategoryList() {
        return ResponseEntity.ok().body(MapperUtils.mapList(categoryRepository.findAll(), Category.class));
    }

}
