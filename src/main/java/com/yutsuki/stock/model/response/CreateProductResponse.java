package com.yutsuki.stock.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateProductResponse {
    private Long productId;

    private Long categoryId;
    private String productName;
    private Integer stockQuantity;
    private Float price;
    private Float cost;

    @Override
    public String toString() {
        return "{" +
                "productId=" + productId +
                ", categoryId=" + categoryId +
                ", productName='" + productName + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", price=" + price +
                ", cost=" + cost +
                '}';
    }
}
