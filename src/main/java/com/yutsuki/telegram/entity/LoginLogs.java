package com.yutsuki.telegram.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "loginLogs")
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginLogs extends BaseEntity implements Serializable {
    @Column(nullable = false, length = 32)
    private String ipv4;
    @JsonIgnore
    @Column(nullable = false)
    private String userAgent;
    @Column(nullable = false, length = 7)
    private String device;
    @ManyToOne
    @JoinColumn(name = "accountId", insertable = false, updatable = false)
    private Account account;
    @Column(name = "accountId")
    private Long uid;

}
