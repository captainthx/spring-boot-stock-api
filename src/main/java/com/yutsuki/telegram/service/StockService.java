package com.yutsuki.telegram.service;

import com.yutsuki.telegram.com.Pagination;
import com.yutsuki.telegram.entity.Product;
import com.yutsuki.telegram.entity.Stock;
import com.yutsuki.telegram.model.StockDetail;
import com.yutsuki.telegram.model.request.CreateStockRequest;
import com.yutsuki.telegram.model.response.StockListResponse;
import com.yutsuki.telegram.repository.ProductRepository;
import com.yutsuki.telegram.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StockService {

    @Resource
    private StockRepository stockRepository;

    @Resource
    private ProductRepository productRepository;


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


    // TODO: 11/13/2023
    // update stock quantity product in stock
    public ResponseEntity<?> updateProductStock() {

        return null;
    }

    public ResponseEntity<?> findStockList(Pagination pagination) {
        Page<Stock> stocks = this.stockRepository.findAll(pagination);

        List<Long> productIds = stocks.stream().map(Stock::getProductId).collect(Collectors.toList());
        Set<String> stockName = stocks.stream().map(Stock::getStockName).collect(Collectors.toSet());

        List<Stock> stockNameList = this.stockRepository.findByStockName(stockName);
        Set<Long> stockIds = stockNameList.stream().map(Stock::getId).collect(Collectors.toSet());
        List<Product> productList = this.productRepository.findAllById(productIds);
        List<Product> productListByStockId = this.productRepository.findAllById(stockIds);

        int totalStockQuantity = productListByStockId.stream().mapToInt(Product::getStockQuantity).sum();
        int totalProduct = this.productRepository.countByStockIn(stockIds);
        StockListResponse stockListResponse = this.buildStockListResponse(productList, totalProduct, totalStockQuantity);
        return ResponseEntity.ok().body(stockListResponse);
    }


    private StockListResponse buildStockListResponse(List<Product> productList, int totalProduct, int totalStockQuantity) {
        return StockListResponse.builder()
                .totalProduct(totalProduct)
                .totalStockQuantity(totalStockQuantity)
                .productDetails(productList.stream().map(product -> {
                            StockDetail stockDetail = new StockDetail();
                            stockDetail.setProductName(product.getProductName());
                            stockDetail.setStockQuantity(product.getStockQuantity());
                            stockDetail.setProductId(product.getId());
                            return stockDetail;
                        }).collect(Collectors.toList())
                ).build();
    }
}