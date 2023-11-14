package com.yutsuki.telegram.com;

import com.yutsuki.telegram.utils.StateMapping;
import lombok.Getter;

import java.util.Objects;
import java.util.Optional;

@Getter
public enum OperateLogsType implements StateMapping<Integer> {
    /**
     * update product
     */
    UPDATE_PRODUCT(10);


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
