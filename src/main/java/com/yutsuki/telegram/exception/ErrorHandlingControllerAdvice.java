package com.yutsuki.telegram.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> msg = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            msg.put("code",HttpStatus.BAD_REQUEST.value());
            msg.put("message", error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(msg);
    }


}
