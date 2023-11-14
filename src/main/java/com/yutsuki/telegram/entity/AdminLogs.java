package com.yutsuki.telegram.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "St_adminLogs")
@Data
@EqualsAndHashCode(callSuper = true)
public class AdminLogs extends BaseEntity implements Serializable {
    @Column(nullable = false)
    private Long uid;
    @Column(nullable = false)
    private String previous;
    @Column(nullable = false)
    private String after;
    private Integer type;
    private LocalDateTime atTime;
}
