package com.yutsuki.telegram.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDetail {
    private Long productId;
    private String productName;
    private int  stockQuantity;
}
