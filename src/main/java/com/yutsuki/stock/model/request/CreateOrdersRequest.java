package com.yutsuki.stock.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrdersRequest {
    private Long productId;
    private int amount;

    @Override
    public String toString() {
        return "{" +
                "productId=" + productId +
                ", amount=" + amount +
                '}';
    }
}
