package com.yutsuki.stock.repository;

import com.yutsuki.stock.entity.St_category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<St_category, Long> {

}
