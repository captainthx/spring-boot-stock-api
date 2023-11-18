package com.yutsuki.stock.repository;

import com.yutsuki.stock.entity.St_account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<St_account, Long> {
    Optional<St_account> findByUsername(String username);
}
