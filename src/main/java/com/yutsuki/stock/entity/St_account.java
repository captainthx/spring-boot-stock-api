package com.yutsuki.stock.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity(name = "ST_account")
@Data
@EqualsAndHashCode(callSuper = true)
public class St_account extends BaseEntity implements Serializable {
    private String username;
    private String password;

    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY,orphanRemoval = true)
    private List<St_loginLogs> loginLogs;

}
