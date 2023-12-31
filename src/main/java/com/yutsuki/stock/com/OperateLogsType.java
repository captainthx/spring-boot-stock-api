package com.yutsuki.stock.com;

import com.yutsuki.stock.utils.StateMapping;
import lombok.Getter;

import java.util.Objects;
import java.util.Optional;

@Getter
public enum OperateLogsType implements StateMapping<Integer> {
    /**
     * update product
     */
    UPDATE_PRODUCT(10),
    /**
     * create stock
     */
    CREATE_STOCK(20),
    /**
     * create category
     */
    CREATE_CATEGORY(30),
    /**
     * create product
     */
    CREATE_PRODUCT(40),
    /**
     * create orders
     */
    CREATE_ORDERS(50);


    private final Integer mapping;

    OperateLogsType(Integer mapping) {
        this.mapping = mapping;
    }

    @Override
    public Integer getMapping() {
        return this.mapping;
    }


    public static Optional<OperateLogsType> find(Integer mapping) {
        Objects.requireNonNull(mapping);
        for (OperateLogsType value : OperateLogsType.values()) {
            if (value.is(mapping)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}
