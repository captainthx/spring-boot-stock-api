package com.yutsuki.telegram.repository;

import com.yutsuki.telegram.entity.St_loginLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogsRepository extends JpaRepository<St_loginLogs, Long> {
}
