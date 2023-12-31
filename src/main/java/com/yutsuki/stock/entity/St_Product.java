package com.yutsuki.stock.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Entity(name = "ST_product")
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
public class St_Product extends BaseEntity implements Serializable {
    @Column(name = "categoryId")
    private Long categoryId;
    private int stockQuantity;
    @Column(length = 100, nullable = false)
    private String productName;
    @Column(nullable = false)
    private Float cost;
    @Column(nullable = false)
    private Float price;
    private String productImage;
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "categoryId", insertable = false, updatable = false)
    private St_category category;




}
