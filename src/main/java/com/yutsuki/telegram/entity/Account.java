package com.yutsuki.telegram.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "account")
@EqualsAndHashCode(callSuper = true)
public class Account extends BaseEntity implements Serializable {
    private String username;
    private String password;

    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY,orphanRemoval = true)
    private List<LoginLogs> loginLogs;

}
