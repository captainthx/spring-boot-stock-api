package com.yutsuki.stock.service;

import com.yutsuki.stock.com.Pagination;
import com.yutsuki.stock.entity.St_adminLogs;
import com.yutsuki.stock.repository.AdminLogsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.yutsuki.stock.com.HandleResponse.successList;

@Service
@Slf4j
public class ManagerService {
    @Resource
    private AdminLogsRepository adminLogsRepository;


    public ResponseEntity<?> findAllAdminLogs(Pagination pagination) {
        Page<St_adminLogs> adminLogsList = this.adminLogsRepository.findAll(pagination);
        return successList(adminLogsList);
    }


}