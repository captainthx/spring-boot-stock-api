package com.yutsuki.telegram.model.response;

import com.yutsuki.telegram.model.StockDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class StockListResponse {
    private List<StockDetail> productDetails;
    private int  totalStockQuantity;
    private int totalProduct;
}
