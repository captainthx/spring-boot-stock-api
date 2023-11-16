package com.yutsuki.telegram.com;

import com.yutsuki.telegram.model.PaginationResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class HandleResponse {
    public static ResponseEntity<?> success() {
        Map<String, Object> msg = new HashMap<>();
        msg.put("message", "success");
        msg.put("code", HttpStatus.OK.value());
        return ResponseEntity.ok(msg);
    }

    public static ResponseEntity<?> successList(Page<?> result) {
        PaginationResponse paginationResponse = PaginationResponse.builder()
                .limit(result.getPageable().getPageSize())
                .pages(result.getTotalPages())
                .current(result.getPageable().getPageNumber() + 1)
                .records((int) result.getTotalElements())
                .build();
        Map<String, Object> res = new HashMap<>();
        res.put("pagination", paginationResponse);
        res.put("result", result.getContent());
        return ResponseEntity.ok(res);
    }
}
