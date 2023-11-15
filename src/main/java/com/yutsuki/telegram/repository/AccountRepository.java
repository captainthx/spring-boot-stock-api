package com.yutsuki.telegram.repository;

import com.yutsuki.telegram.entity.St_account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<St_account, Long> {
    Optional<St_account> findByUsername(String username);
}
