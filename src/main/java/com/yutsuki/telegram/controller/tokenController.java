package com.yutsuki.telegram.controller;

import com.yutsuki.telegram.model.response.TokenResponse;
import com.yutsuki.telegram.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/token")
public class tokenController {


    @Resource
    private TokenService tokenService;

    @GetMapping
    ResponseEntity<?> getToken(Authentication authentication) {
//        TokenResponse token = this.tokenService.generateToken(1L);
//        return ResponseEntity.ok(token);
        return null;
    }

}
