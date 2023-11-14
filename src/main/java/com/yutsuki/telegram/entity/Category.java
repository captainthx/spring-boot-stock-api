package com.yutsuki.telegram.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "ST_category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String categoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Product> product;

}
