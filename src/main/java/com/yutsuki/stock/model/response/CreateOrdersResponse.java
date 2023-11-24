package com.yutsuki.stock.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateOrdersResponse {
    private String ordersId;
    private Long productId;
    private Long uid;
    private String productName;
    private Integer status;
}
