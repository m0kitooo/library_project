package com.app.libraryproject.controller.exception;

import com.app.libraryproject.exception.IllegalRequestArgumentException;
import com.app.libraryproject.exception.IllegalResponseArgumentException;
import com.app.libraryproject.exception.InvalidRequestArgumentException;
import com.app.libraryproject.exception.RecordNotFoundException;
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

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<String> foo(RecordNotFoundException error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalRequestArgumentException.class)
    public ResponseEntity<String> foo(IllegalRequestArgumentException error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalResponseArgumentException.class)
    public ResponseEntity<String> foo(IllegalResponseArgumentException error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}