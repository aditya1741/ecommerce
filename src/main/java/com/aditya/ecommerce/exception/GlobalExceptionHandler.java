package com.aditya.ecommerce.exception;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(@NotNull UserNotFoundException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(@NotNull ProductNotFoundException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
