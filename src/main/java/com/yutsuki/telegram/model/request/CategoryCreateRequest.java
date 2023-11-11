package com.yutsuki.telegram.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreateRequest {
    private String categoryName;

    @Override
    public String toString() {
        return "{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }
}
