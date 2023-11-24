package com.yutsuki.stock.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdStockProductRequest {
    private Long productId;
    private int stockQuantity;
    private Float cost;
    private Float price;

    @Override
    public String toString() {
        return "{" +
                "productId=" + productId +
                ", stockQuantity=" + stockQuantity +
                ", cost=" + cost +
                ", price=" + price +
                '}';
    }
}
