package com.yutsuki.telegram.service;

import com.yutsuki.telegram.com.Pagination;
import com.yutsuki.telegram.entity.St_account;
import com.yutsuki.telegram.entity.St_loginLogs;
import com.yutsuki.telegram.repository.AccountRepository;
import com.yutsuki.telegram.repository.LoginLogsRepository;
import com.yutsuki.telegram.utils.Comm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.yutsuki.telegram.com.HandleResponse.successList;

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
