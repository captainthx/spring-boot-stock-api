package com.yutsuki.stock.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NotificationsRequest {
    private String notifications;

    @Override
    public String toString() {
        return "{" +
                "message=" + notifications +
                '}';
    }
}
