package com.yutsuki.stock.model.response;

import com.yutsuki.stock.model.StockDetail;
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
