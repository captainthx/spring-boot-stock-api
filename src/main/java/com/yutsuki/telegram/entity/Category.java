package com.yutsuki.telegram.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long CategoryId;
    @Column(length = 50,nullable = false)
    private String categoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Product> product;

}
