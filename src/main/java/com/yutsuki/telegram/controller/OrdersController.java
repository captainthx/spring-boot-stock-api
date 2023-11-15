package com.yutsuki.telegram.controller;

import com.yutsuki.telegram.service.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/orders")
public class OrdersController {
    @Resource
    private OrdersService ordersService;

    public ResponseEntity<?> createOrders() {
        return ordersService.createOrders();
    }
}
