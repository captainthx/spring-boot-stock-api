package com.yutsuki.telegram.service;

import com.yutsuki.telegram.com.Pagination;
import com.yutsuki.telegram.entity.Stock;
import com.yutsuki.telegram.model.request.CreateStockRequest;
import com.yutsuki.telegram.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StockService {

    @Resource
    private StockRepository stockRepository;


    public ResponseEntity<?> createStock(CreateStockRequest request) {
        List<Stock> stockList = request.getProductIds().stream().map(productId -> {
            Stock stock = new Stock();
            stock.setStockName(request.getStockName());
            stock.setProductId(productId);
            return stock;
        }).collect(Collectors.toList());
        List<Stock> stocks = this.stockRepository.saveAll(stockList);
        return ResponseEntity.ok().body(stocks);
    }

    public ResponseEntity<?> getStocks(Pagination pagination) {
        Page<Stock> stocks = this.stockRepository.findAll(pagination);
        return ResponseEntity.ok().body(stocks);
    }
}
