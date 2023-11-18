package com.yutsuki.telegram.service;

import com.yutsuki.telegram.repository.OrdersRepository;
import com.yutsuki.telegram.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StatisticService {
    @Resource
    private OrdersRepository ordersRepository;
    @Resource
    private ProductRepository productRepository;

}
