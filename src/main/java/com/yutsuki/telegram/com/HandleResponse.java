package com.yutsuki.telegram.com;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class HandleResponse {
    public static ResponseEntity<?> success(){
        Map<String, Object> msg = new HashMap<>();
        msg.put("message", "success");
        msg.put("code", HttpStatus.OK.value());
        return ResponseEntity.ok().body(msg);
    }
}
