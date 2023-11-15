package com.yutsuki.telegram.repository;

import com.yutsuki.telegram.entity.St_Product;
import com.yutsuki.telegram.entity.St_stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<St_Product, Long> {
    Page<St_Product> findAll(Pageable pageable);

    int countByStock(St_stock stock);
    @Query(value = "select count(*) from product where stock_id is not null",nativeQuery = true)
    int countByStockIn(Set<Long> stockIds) ;
    @Query(value = "select * from product where stock_id is not null",nativeQuery = true)
    List<St_Product> findAllByStockId(Set<Long> stockIds);
    List<St_Product> findAllByStockId(Long stockId);
    int countByStockId(Long stockId);
}
