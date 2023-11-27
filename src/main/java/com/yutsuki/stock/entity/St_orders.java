package com.yutsuki.stock.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Entity(name = "ST_orders")
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
public class St_orders extends BaseEntity implements Serializable {
    private String ordersId;
    private Long productId;
    private Integer amount;
    private Integer status;
    private Long uid;
}
