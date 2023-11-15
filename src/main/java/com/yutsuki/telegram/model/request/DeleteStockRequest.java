package com.yutsuki.telegram.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteStockRequest {
    private Long stockId;

    @Override
    public String toString() {
        return "{" +
                "stockId=" + stockId +
                '}';
    }
}
