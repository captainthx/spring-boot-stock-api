package com.yutsuki.telegram.controller;

import com.yutsuki.telegram.model.request.CreateStockRequest;
import com.yutsuki.telegram.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/stock")
public class StockController {
    @Resource
    private StockService stockService;

    @PostMapping()
    public ResponseEntity<?> createStock(@Valid @RequestBody CreateStockRequest request) {
        return stockService.createStock(request);
    }
}
