package com.yutsuki.stock.service;

import com.yutsuki.stock.entity.St_account;
import com.yutsuki.stock.model.Token;
import com.yutsuki.stock.model.response.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.convert.DurationStyle;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@Slf4j
public class TokenService {
    @Resource
    private JwtEncoder jwtEncoder;
    @Resource
    private JwtDecoder jwtDecoder;

    @Resource
    private RedisService redisService;

    @Value("${jwt.expire}")
    private String expire;
    @Value("${jwt.issuer}")
    private String issuer;


    public TokenResponse generateToken(St_account account) {
        Token access = this.generateAccessToken(account.getId());
        Token refresh = this.generateRefreshToken(account.getId());
        this.setTokenId(access.getToken(), refresh.getToken(), account.getId());
        return TokenResponse.builder()
                .accessToken(access.getToken())
                .refreshToken(refresh.getToken())
                .accessTokenExpire(access.getExpire().toEpochMilli())
                .refreshTokenExpire(refresh.getExpire().toEpochMilli())
                .build();
    }

    public Token generateAccessToken(Long uid) {
        return generate(this.setAccessExpire(expire), uid);
    }

    public Token generateRefreshToken(Long uid) {
        return generate(this.setRefreshExpire(expire),uid);
    }

    private Token generate(Instant expire, Long uid) {

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .id(UUID.randomUUID().toString())
                .issuer(issuer)
                .issuedAt(Instant.now())
                .claim("id", uid)
                .expiresAt(expire)
                .build();
        return Token.builder()
                .token(jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue())
                .expire(expire)
                .build();
    }

    private void setTokenId(String accessToken, String refreshToken, Long uid) {
        redisService.set("access:" + uid, this.getTokenId(accessToken));
        redisService.set("refresh:" + uid, this.getTokenId(refreshToken));
    }

    public String jwtDecode(String token) {
        Jwt decode = jwtDecoder.decode(token);
        return decode.getClaims().get("name").toString();
    }

    public String getTokenId(String token) {
        Jwt decode = jwtDecoder.decode(token);
        return decode.getId();
    }


    private Instant setRefreshExpire(String expirationMs) {
        return Instant.now().plusMillis(DurationStyle.detectAndParse(expirationMs).toMillis()).plus(30, ChronoUnit.MINUTES);
    }

    private Instant setAccessExpire(String expirationMs) {
        return Instant.now().plusMillis(DurationStyle.detectAndParse(expirationMs).toMillis());
    }
}


