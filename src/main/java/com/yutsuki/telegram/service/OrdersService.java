package com.yutsuki.telegram.service;

import com.yutsuki.telegram.repository.OrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrdersService {
    @Resource
    private OrdersRepository ordersRepository;


    public ResponseEntity<?>createOrders(){
        return null;
    }
}
