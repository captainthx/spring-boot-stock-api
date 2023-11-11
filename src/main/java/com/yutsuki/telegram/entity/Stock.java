package com.yutsuki.telegram.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity(name = "stock")
@Data
@EqualsAndHashCode(callSuper = true)
public class Stock extends BaseEntity implements Serializable {
    private String stockName;
    private Long productId;

    @OneToMany(mappedBy = "stock")
    @ToString.Exclude
    private List<Product> products;

}
