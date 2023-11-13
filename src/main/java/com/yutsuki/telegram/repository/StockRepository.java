package com.yutsuki.telegram.repository;

import com.yutsuki.telegram.entity.Stock;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface StockRepository extends JpaRepository<Stock,Long> {
    Page<Stock>findAll(Pageable pageable);
    @Query(value = "select * from stock where stock_name in (:stockName)",nativeQuery = true)
    List<Stock>findByStockName(@Param("stockName") Set<String> stockName);
}
