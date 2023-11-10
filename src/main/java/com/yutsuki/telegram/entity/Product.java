package com.yutsuki.telegram.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "product")
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {
    private Long categoryId;
    private int stockQuantity;
    @Column(length = 100,nullable = false)
    private String productName;
    @Column(nullable = false)
    private Float price;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "categoryId", insertable = false, updatable = false)
    private Category category;
}
