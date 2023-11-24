package com.yutsuki.stock.com;

import com.yutsuki.stock.utils.StateMapping;
import lombok.Getter;

import java.util.Objects;
import java.util.Optional;

@Getter
public enum OrdersStatusType implements StateMapping<Integer> {
    /**
     * pending
     */
    PENDING(10),
    /**
     * success
     */
    SUCCESS(20),
    /**
     * cancel
     */
    CANCEL(30),
   ;


    private final Integer mapping;

    OrdersStatusType(Integer mapping) {
        this.mapping = mapping;
    }

    @Override
    public Integer getMapping() {
        return this.mapping;
    }


    public static Optional<OrdersStatusType> find(Integer mapping) {
        Objects.requireNonNull(mapping);
        for (OrdersStatusType value : OrdersStatusType.values()) {
            if (value.is(mapping)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}
