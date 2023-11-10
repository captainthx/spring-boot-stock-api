package com.yutsuki.telegram.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class HandleException {

    public static ResponseEntity<?>exception(String message ){
        Map<String, Object> msg = new HashMap<>();
        msg.put("message", message);
        msg.put("code", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(msg);
    }
}
