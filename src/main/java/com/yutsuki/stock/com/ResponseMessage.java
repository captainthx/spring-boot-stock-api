package com.yutsuki.stock.com;

import com.yutsuki.stock.utils.StateMapping;
import lombok.Getter;

@Getter
public enum ResponseMessage implements StateMapping<String> {
    INVALID_ACCOUNT("invalid account"),
    INVALID_USERNAME("invalid username"),
    INVALID_PASSWORD("invalid password"),
    PASSWORD_NOT_MATCH("password not match"),
    ;

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMapping() {
        return this.message;
    }
}
