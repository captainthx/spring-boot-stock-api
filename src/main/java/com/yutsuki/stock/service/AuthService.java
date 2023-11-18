package com.yutsuki.stock.service;

import com.yutsuki.stock.com.ResponseMessage;
import com.yutsuki.stock.entity.St_account;
import com.yutsuki.stock.entity.St_loginLogs;
import com.yutsuki.stock.model.request.LoginRequest;
import com.yutsuki.stock.model.request.RegisterAccountRequest;
import com.yutsuki.stock.model.response.RegisterAccountResponse;
import com.yutsuki.stock.repository.AccountRepository;
import com.yutsuki.stock.repository.LoginLogsRepository;
import com.yutsuki.stock.utils.Comm;
import com.yutsuki.stock.utils.MapperUtils;
import com.yutsuki.stock.utils.ValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.yutsuki.stock.exception.HandleException.exception;

@Service
@Slf4j
public class AuthService {
    @Resource
    private TokenService tokenService;
    @Resource
    private AccountRepository accountRepository;
    @Resource
    private LoginLogsRepository loginLogsRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> register(RegisterAccountRequest request) {
        if (ValidateUtil.invalidUsername(request.getUsername())) {
            log.warn("Register::(block). [username is null]. req. {}", request);
            return exception(ResponseMessage.INVALID_USERNAME.getMessage());
        }
        if (ValidateUtil.invalidPassword(request.getPassword())) {
            log.warn("Register::(block). [password is null]. req{}", request);
            return exception(ResponseMessage.INVALID_PASSWORD.getMessage());
        }

        St_account entity = new St_account();
        entity.setUsername(request.getUsername());
        entity.setPassword(this.passwordEncoder.encode(request.getPassword()));
        St_account response = this.accountRepository.save(entity);
        RegisterAccountResponse registerAccountResponse = MapperUtils.mapOne(response, RegisterAccountResponse.class);
        return ResponseEntity.ok(registerAccountResponse);
    }

    public ResponseEntity<?> login(LoginRequest request, HttpServletRequest httpServletRequest) {
        String ipv4 = Comm.getIpAddress(httpServletRequest);
        String userAgent = Comm.getUserAgent(httpServletRequest);
        String deviceType = Comm.getDeviceType(userAgent);

        Optional<St_account> optionalAccount = this.accountRepository.findByUsername(request.getUsername());
        if (!optionalAccount.isPresent()) {
            log.warn("Login::(block). [account not found]. req{}", request);
            return exception(ResponseMessage.INVALID_USERNAME.getMessage());
        }
        St_account account = optionalAccount.get();
        if (!this.passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            log.warn("Login::(block). [password not match]. req{}", request);
            return exception(ResponseMessage.PASSWORD_NOT_MATCH.getMessage());
        }
        // set login logs
        St_loginLogs loginLogs = new St_loginLogs();
        loginLogs.setUid(account.getId());
        loginLogs.setDevice(deviceType);
        loginLogs.setIpv4(ipv4);
        loginLogs.setUserAgent(userAgent);
        this.loginLogsRepository.save(loginLogs);

        return ResponseEntity.ok(this.tokenService.generateToken(account));
    }

}
