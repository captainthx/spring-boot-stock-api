package com.yutsuki.telegram.controller;

import com.yutsuki.telegram.com.Pagination;
import com.yutsuki.telegram.model.request.CreateOrdersRequest;
import com.yutsuki.telegram.service.OrdersService;
import lombok.Getter;
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
