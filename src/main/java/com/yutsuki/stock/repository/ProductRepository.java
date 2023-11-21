package com.yutsuki.stock.repository;

import com.yutsuki.stock.entity.St_Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<St_Product, Long> {
    Page<St_Product> findAll(Pageable pageable);
    Page<St_Product> findByCategoryId(Long categoryId, Pageable pageable);

}
