package com.yutsuki.telegram.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateRequest {
    private String productName;
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
