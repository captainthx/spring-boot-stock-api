package com.yutsuki.stock.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateProductResponse {
    private Long id;
    private Long categoryId;
    private String productName;
    private Integer stockQuantity;
    private Float price;
    private Float cost;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", productName='" + productName + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", price=" + price +
                ", cost=" + cost +
                '}';
    }
}
