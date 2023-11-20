package com.yutsuki.stock.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yutsuki.stock.utils.JsonUtils;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Column(name = "cdt",columnDefinition = "TIMESTAMP")
    @JsonSerialize(using = JsonUtils.JsonTimestampSerializer.class)
    private LocalDateTime cdt;
    @UpdateTimestamp
    @JsonSerialize(using = JsonUtils.JsonTimestampSerializer.class)
    private LocalDateTime udt;
}
