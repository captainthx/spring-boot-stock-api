package com.yutsuki.stock.repository;

import com.yutsuki.stock.entity.St_adminLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminLogsRepository extends JpaRepository<St_adminLogs, Long> {
}
