package com.yutsuki.stock.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRequest {
    private String refreshToken;

    @Override
    public String toString() {
        return "{" +
                "refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
