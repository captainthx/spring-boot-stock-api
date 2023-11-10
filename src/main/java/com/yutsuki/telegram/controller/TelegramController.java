package com.yutsuki.telegram.controller;

import com.yutsuki.telegram.model.request.MsgRequest;
import com.yutsuki.telegram.service.TelegramService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/telegram")
public class TelegramController {
    @Resource
    private TelegramService telegramService;


    @PostMapping
    public void sendMessage(@RequestBody MsgRequest request) {
        this.telegramService.sendMessage(request);
    }


    @GetMapping
    public void getauth(Authentication authentication) {

    }
}
