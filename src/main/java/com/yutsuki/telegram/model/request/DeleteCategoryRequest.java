package com.yutsuki.telegram.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteCategoryRequest {
    private Long categoryId;

    @Override
    public String toString() {
        return "{" +
                "categoryId=" + categoryId +
                '}';
    }
}
