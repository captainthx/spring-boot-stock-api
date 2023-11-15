package com.yutsuki.telegram.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity(name = "ST_orders")
@Data
@EqualsAndHashCode(callSuper = true)
public class St_order extends BaseEntity implements Serializable {
    private Long productId;
    private Float amountQuantity;
}
