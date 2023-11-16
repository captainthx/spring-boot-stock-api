package com.yutsuki.telegram.repository;

import com.yutsuki.telegram.entity.St_orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<St_orders,Long> {
    Page<St_orders>findAll(Pageable pageable);
}
