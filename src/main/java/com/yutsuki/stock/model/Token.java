package com.yutsuki.stock.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class Token {
    private String token;
    private Instant expire;
}
