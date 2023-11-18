package com.yutsuki.stock.repository;

import com.yutsuki.stock.entity.St_stock;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface StockRepository extends JpaRepository<St_stock,Long> {
    Page<St_stock>findAll(Pageable pageable);
    @Query(value = "select * from stock where stock_name in (:stockName)",nativeQuery = true)
    List<St_stock>findByStockName(@Param("stockName") Set<String> stockName);
}
