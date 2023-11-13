package com.yutsuki.telegram.repository;

import com.yutsuki.telegram.entity.Product;
import com.yutsuki.telegram.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);

    int countByStock(Stock stock);
    @Query(value = "select count(*) from product where stock_id is not null",nativeQuery = true)
    int countByStockIn(Set<Long> stockIds) ;
}
