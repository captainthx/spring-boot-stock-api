package com.yutsuki.stock.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventListener {


    @EventListener(OrderEvent.class)
    public void orderEvent(OrderEvent orderEvent) {

    }
}
