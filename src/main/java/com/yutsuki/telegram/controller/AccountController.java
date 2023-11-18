package com.yutsuki.telegram.controller;

import com.yutsuki.telegram.com.Pagination;
import com.yutsuki.telegram.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/account")
public class AccountController {
    @Resource
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<?> historyLogin(Pagination pagination ){
        return this.accountService.historyLoginList(pagination);
    }
}
