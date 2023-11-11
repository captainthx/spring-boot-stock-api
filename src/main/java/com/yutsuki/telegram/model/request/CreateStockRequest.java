package com.yutsuki.telegram.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateStockRequest {
    private String stockName;
    List<Long>productIds;
}
