package com.yutsuki.stock.service;

import com.yutsuki.stock.com.Pagination;
import com.yutsuki.stock.entity.St_loginLogs;
import com.yutsuki.stock.repository.LoginLogsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.yutsuki.stock.com.HandleResponse.successList;

@Service
@Slf4j
public class AccountService {
    @Resource
    private LoginLogsRepository loginLogsRepository;

    public ResponseEntity<?> historyLoginList(Pagination pagination ) {
        Page<St_loginLogs> loginLogs = this.loginLogsRepository.findAll(pagination);
        return successList(loginLogs);
    }
}
