package com.yutsuki.stock;

import co.omise.Client;
import co.omise.models.Charge;
import co.omise.models.Source;
import co.omise.models.SourceType;
import co.omise.requests.Request;
import com.yutsuki.stock.model.response.CreatePaymentResponse;
import com.yutsuki.stock.omise.OmiseService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class devApplication implements ApplicationRunner {

    @Resource
    private OmiseService omiseService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Hello World from dev Runner");

    }
}
