package com.yutsuki.telegram.service;

import com.yutsuki.telegram.com.ResponseMessage;
import com.yutsuki.telegram.entity.Account;
import com.yutsuki.telegram.exception.ErrorHandlingControllerAdvice;
import com.yutsuki.telegram.exception.HandleException.*;
import com.yutsuki.telegram.model.request.LoginRequest;
import com.yutsuki.telegram.model.request.RegisterAccountRequest;
import com.yutsuki.telegram.model.response.RegisterAccountResponse;
import com.yutsuki.telegram.repository.AccountRepository;
import com.yutsuki.telegram.utils.MapperUtils;
import com.yutsuki.telegram.utils.ValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static com.yutsuki.telegram.exception.HandleException.exception;

@Service
@Slf4j
public class AuthService {
    @Resource
    private TokenService tokenService;
    @Resource
    private AccountRepository accountRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> register(RegisterAccountRequest request) {
        if (ValidateUtil.invalidUsername(request.getUsername())) {
            log.warn("Register account (block). [username is null]. req. {}", request);
            return exception(ResponseMessage.INVALID_USERNAME.getMessage());
        }
        if (ValidateUtil.invalidPassword(request.getPassword())) {
            log.warn("Register account (block). [password is null]. req{}", request);
            return exception(ResponseMessage.INVALID_PASSWORD.getMessage());
        }

        Account entity = new Account();
        entity.setUsername(request.getUsername());
        entity.setPassword(this.passwordEncoder.encode(request.getPassword()));
        Account response = this.accountRepository.save(entity);
        RegisterAccountResponse registerAccountResponse = MapperUtils.mapOne(response, RegisterAccountResponse.class);
        return ResponseEntity.ok(registerAccountResponse);
    }

    public ResponseEntity<?> login(LoginRequest request) {
        Optional<Account> optionalAccount = this.accountRepository.findByUsername(request.getUsername());
        if (!optionalAccount.isPresent()) {
            log.warn("Login (block). [account not found]. req{}", request);
            return exception(ResponseMessage.INVALID_ACCOUNT.getMessage());
        }
        Account account = optionalAccount.get();
        if (!this.passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            log.warn("Login (block). [password not match]. req{}", request);
            return exception(ResponseMessage.PASSWORD_NOT_MATCH.getMessage());
        }
        return ResponseEntity.ok(this.tokenService.generateToken(account));
    }

}
