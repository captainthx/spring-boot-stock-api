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
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.yutsuki.telegram.exception.HandleException.exception;

@Service
@Slf4j
public class StockService {

    @Resource
    private StockRepository stockRepository;

    @Resource
    private ProductRepository productRepository;


    public ResponseEntity<?> createStock(CreateStockRequest request) {
        if (!StringUtils.hasText(request.getStockName())) {
            log.warn("Stock::(block). Stock name is empty req. {}", request);
            return exception("Stock name is empty");
        }
        Stock entity = new Stock();
        entity.setStockName(request.getStockName());
        Stock res = this.stockRepository.save(entity);
        return ResponseEntity.ok().body(res);
    }

    public ResponseEntity<?> findStockList(Pagination pagination) {
        List<Stock> stockListAll = this.stockRepository.findAll().stream().map(e -> {
            Stock stock = new Stock();
            stock.setId(e.getId());
            stock.setStockName(e.getStockName());
            return stock;
        }).collect(Collectors.toList());
        Map<Long, Stock> stockMap = stockListAll.stream().collect(Collectors.toMap(Stock::getId, Function.identity()));

        List<StockListResponse> responseList = this.productRepository.findAll(pagination).stream()
                .collect(Collectors.groupingBy(
                        e -> stockMap.get(e.getStockId()).getStockName(),
                        Collectors.mapping(e -> {
                            StockDetail stockDetail = new StockDetail();
                            stockDetail.setStockQuantity(e.getStockQuantity());
                            stockDetail.setProductName(e.getProductName());
                            stockDetail.setProductId(e.getId());
                            return stockDetail;
                        }, Collectors.toList())
                ))
                .entrySet().stream()
                .map(entry -> {
                    StockListResponse stockListResponse = new StockListResponse();
                    stockListResponse.setStockName(entry.getKey());
                    stockListResponse.setProductDetails(entry.getValue());
                    stockListResponse.setTotalStockQuantity(entry.getValue().stream().mapToInt(StockDetail::getStockQuantity).sum());
                    stockListResponse.setTotalProduct(entry.getValue().size());
                    return stockListResponse;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(responseList);
    }


}