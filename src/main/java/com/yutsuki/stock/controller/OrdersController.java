package com.yutsuki.stock.controller;

import com.yutsuki.stock.com.Pagination;
import com.yutsuki.stock.model.request.CreateOrdersRequest;
import com.yutsuki.stock.service.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/orders")
public class OrdersController {
    @Resource
    private OrdersService ordersService;

    @PostMapping
    public ResponseEntity<?> createOrders(@RequestBody CreateOrdersRequest request, Authentication authentication) {
        return ordersService.createOrders(request,authentication);
    }
    @GetMapping
    public ResponseEntity<?> findOrdersAll(Pagination pagination) {
        return this.ordersService.findAllOrders(pagination);
    }
}
