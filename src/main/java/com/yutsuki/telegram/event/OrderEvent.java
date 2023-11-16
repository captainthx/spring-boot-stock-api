package com.yutsuki.telegram.event;

import com.yutsuki.telegram.entity.St_orders;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.security.core.Authentication;

import java.time.Clock;

@Getter
public class OrderEvent extends ApplicationEvent {
    private final St_orders order;
    private final Authentication authentication;

    public OrderEvent(Object source, St_orders order, Authentication authentication) {
        super(source);
        this.order = order;
        this.authentication = authentication;
    }

    public OrderEvent(Object source, Clock clock, St_orders order, Authentication authentication) {
        super(source, clock);
        this.order = order;
        this.authentication = authentication;
    }

}
