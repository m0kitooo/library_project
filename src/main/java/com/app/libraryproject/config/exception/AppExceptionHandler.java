package com.app.libraryproject.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> foo() {
        return new ResponseEntity<>("Hello from app exception handler", HttpStatus.BAD_REQUEST);
    }
}
