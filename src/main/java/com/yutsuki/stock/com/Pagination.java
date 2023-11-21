package com.yutsuki.stock.com;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


public class Pagination extends PageRequest {
    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_LIMIT = 10;
    @Getter
    @Setter
    private Long categoryId;

    public Pagination(Integer page, Integer size, Long categoryId) {
        super(page != null && page > 0 ? page - 1 : DEFAULT_PAGE, size != null && size > 0 ? size : DEFAULT_LIMIT, Sort.unsorted());
        this.categoryId = categoryId;
    }

}
