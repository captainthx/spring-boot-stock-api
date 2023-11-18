package com.yutsuki.stock.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateOrdersResponse {
    private Long productId;
    private String productName;
    private Float totalPrice;
    private Float costPerProduct;
    private Float pricePerProduct;
    private int  totalQuantity;
}
