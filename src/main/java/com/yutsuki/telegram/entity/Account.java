package com.yutsuki.telegram.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;

@Data
@Entity(name = "account")
@EqualsAndHashCode(callSuper = true)
public class Account extends BaseEntity {
    private String username;
    private String password;
}
