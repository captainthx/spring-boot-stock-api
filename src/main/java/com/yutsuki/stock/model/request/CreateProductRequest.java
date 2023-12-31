package com.yutsuki.stock.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequest {
    private String productName;
    private String productImage;
    private Float price;
    private Float cost;
    private Integer stockQuantity;
    private Long categoryId;

    @Override
    public String toString() {
        return "{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                ", cost=" + cost +
                ", stockQuantity=" + stockQuantity +
                ", categoryId=" + categoryId +
                '}';
    }
}
