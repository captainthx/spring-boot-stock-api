package com.yutsuki.telegram.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdStockProductRequest {
    private Long productId;
    private int stockQuantity;
    private Long categoryId;
    private Long  stockId;
    private Float cost;
    private Float price;

    @Override
    public String toString() {
        return "{" +
                "productId=" + productId +
                ", stockQuantity=" + stockQuantity +
                ", categoryId=" + categoryId +
                ", stockId=" + stockId +
                ", cost=" + cost +
                ", price=" + price +
                '}';
    }
}
