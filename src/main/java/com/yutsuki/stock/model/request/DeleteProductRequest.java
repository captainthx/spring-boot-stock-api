package com.yutsuki.stock.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteProductRequest {
    private Long productId;

    @Override
    public String toString() {
        return "{" +
                "productId=" + productId +
                '}';
    }
}
