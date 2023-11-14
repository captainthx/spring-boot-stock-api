package com.yutsuki.telegram.model.response;

import com.yutsuki.telegram.model.StockDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StockListResponse {
    private String stockName;
    private List<StockDetail>  productDetails;
    private int  totalStockQuantity;
    private int totalProduct;
}
