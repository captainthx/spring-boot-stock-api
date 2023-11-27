package com.yutsuki.stock.controller;

import com.yutsuki.stock.com.Pagination;
import com.yutsuki.stock.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/manager")
public class ManagerController {
    @Resource
    private ManagerService managerService;

    @GetMapping
    public ResponseEntity<?>findAllAdminLogs(Pagination pagination){
        return this.managerService.findAllAdminLogs(pagination);
    }
}
