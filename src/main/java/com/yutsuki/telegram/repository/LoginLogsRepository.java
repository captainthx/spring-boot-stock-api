package com.yutsuki.telegram.repository;

import com.yutsuki.telegram.entity.St_loginLogs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginLogsRepository extends JpaRepository<St_loginLogs, Long> {

    Page<St_loginLogs> findAll(Pageable pageable);
}
