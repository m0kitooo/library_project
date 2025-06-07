package com.app.libraryproject.controller.exception;

import com.app.libraryproject.exception.InvalidRequestArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(InvalidRequestArgumentException.class)
    public ResponseEntity<String> foo(InvalidRequestArgumentException error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
