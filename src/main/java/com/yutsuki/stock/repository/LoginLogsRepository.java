package com.yutsuki.stock.repository;

import com.yutsuki.stock.entity.St_loginLogs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogsRepository extends JpaRepository<St_loginLogs, Long> {

    Page<St_loginLogs> findAll(Pageable pageable);
}
