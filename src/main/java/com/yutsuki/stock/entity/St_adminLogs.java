package com.yutsuki.stock.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yutsuki.stock.utils.JsonUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "St_adminLogs")
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
public class St_adminLogs extends BaseEntity implements Serializable {
    @Column(nullable = false)
    private Long uid;
    @Column(nullable = false)
    @Lob
    private String previous;
    @Column(nullable = false)
    @Lob
    private String after;
    private Integer type;
    @Column(nullable = false)
    @JsonSerialize(using = JsonUtils.JsonTimestampSerializer.class)
    private LocalDateTime atTime;
}
