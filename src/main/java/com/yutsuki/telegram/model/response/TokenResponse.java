package com.yutsuki.telegram.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpire;
    private Long refreshTokenExpire;
}
