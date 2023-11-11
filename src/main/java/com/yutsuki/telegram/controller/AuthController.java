package com.yutsuki.telegram.controller;

import com.yutsuki.telegram.model.request.LoginRequest;
import com.yutsuki.telegram.model.request.RegisterAccountRequest;
import com.yutsuki.telegram.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @Resource
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterAccountRequest request) {
        return this.authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request , HttpServletRequest httpServletRequest) {
        return this.authService.login(request,httpServletRequest);
    }

}
