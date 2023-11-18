package com.yutsuki.stock.repository;

import com.yutsuki.stock.entity.St_orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<St_orders,Long> {
    Page<St_orders>findAll(Pageable pageable);
}
