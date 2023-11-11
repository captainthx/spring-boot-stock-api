package com.yutsuki.telegram.repository;

import com.yutsuki.telegram.entity.LoginLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogsRepository extends JpaRepository<LoginLogs, Long> {
}
