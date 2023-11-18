package com.yutsuki.telegram.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity(name = "ST_loginLogs")
@Data
@EqualsAndHashCode(callSuper = true)
public class St_loginLogs extends BaseEntity implements Serializable {
    @Column(nullable = false, length = 32)
    private String ipv4;
    @JsonIgnore
    @Column(nullable = false)
    private String userAgent;
    @Column(nullable = false, length = 7)
    private String device;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "accountId", insertable = false, updatable = false)
    private St_account account;
    @Column(name = "accountId")
    private Long uid;

}
