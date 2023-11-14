package com.yutsuki.telegram.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "ST_stock")
@Data
public class Stock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stockName;

    @OneToMany(mappedBy = "stock",fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonIgnore
    private List<Product> products;

}
