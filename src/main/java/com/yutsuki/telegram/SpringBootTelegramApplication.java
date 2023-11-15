package com.yutsuki.telegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class SpringBootTelegramApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTelegramApplication.class, args);
    }

}
