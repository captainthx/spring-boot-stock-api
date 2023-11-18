package com.yutsuki.stock.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity(name = "ST_orders")
@Data
@EqualsAndHashCode(callSuper = true)
public class St_orders extends BaseEntity implements Serializable {
    private Long productId;
    private int totalQuantity;
    private Float totalPrice;
    private Float costPerProduct;
    private Float pricePerProduct;
}
