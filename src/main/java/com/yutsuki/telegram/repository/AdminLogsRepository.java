package com.yutsuki.telegram.repository;

import com.yutsuki.telegram.entity.AdminLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminLogsRepository extends JpaRepository<AdminLogs, Long> {
}
