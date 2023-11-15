package com.yutsuki.telegram.repository;

import com.yutsuki.telegram.entity.St_adminLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminLogsRepository extends JpaRepository<St_adminLogs, Long> {
}
