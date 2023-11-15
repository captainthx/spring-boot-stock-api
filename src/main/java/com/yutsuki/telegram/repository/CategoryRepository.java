package com.yutsuki.telegram.repository;

import com.yutsuki.telegram.entity.St_category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<St_category, Long> {

}
