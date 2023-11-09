package com.yutsuki.telegram.controller;

import com.yutsuki.telegram.model.request.MsgRequest;
import com.yutsuki.telegram.service.TelegramService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
