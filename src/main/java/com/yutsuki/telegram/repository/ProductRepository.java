package com.yutsuki.telegram.repository;

import com.yutsuki.telegram.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
