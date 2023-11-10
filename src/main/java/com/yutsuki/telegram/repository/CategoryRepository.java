package com.yutsuki.telegram.repository;

import com.yutsuki.telegram.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
