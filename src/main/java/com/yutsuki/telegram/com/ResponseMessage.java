package com.yutsuki.telegram.com;

public enum ResponseMessage {
    INVALID_ACCOUNT("invalid account"),
    INVALID_USERNAME("invalid username"),
    INVALID_PASSWORD("invalid password"),
    PASSWORD_NOT_MATCH("password not match"),
    ;

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
