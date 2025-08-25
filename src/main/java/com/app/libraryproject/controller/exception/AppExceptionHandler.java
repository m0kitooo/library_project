package com.app.libraryproject.controller.exception;

import com.app.libraryproject.dto.error.AppErrorResponse;
import com.app.libraryproject.exception.ApiException;
import com.app.libraryproject.exception.InvalidRequestArgumentException;
import com.app.libraryproject.exception.InvalidResponseArgumentException;
import com.app.libraryproject.exception.RecordConflictException;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.exception.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
    // @ExceptionHandler(InvalidRequestArgumentException.class)
    // public ResponseEntity<AppErrorResponse> handleError(InvalidRequestArgumentException error) {
    //     return new ResponseEntity<>(
    //         new AppErrorResponse(error.getMessage()),
    //         HttpStatus.BAD_REQUEST
    //     );
    // }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<AppErrorResponse> handleApiException(ApiException ex) {
        return new ResponseEntity<>(ex.toAppErrorResponse(), ex.getStatus());
    }

    @ExceptionHandler(InvalidResponseArgumentException.class)
    public ResponseEntity<String> handleError(InvalidResponseArgumentException error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<String> handleError(RecordNotFoundException error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecordConflictException.class)
    public ResponseEntity<String> handleError(RecordConflictException error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<AppErrorResponse> handleError(ResourceNotFoundException exception) {
        return new ResponseEntity<>(
            new AppErrorResponse(exception.getError().code(), exception.getMessage()),
            exception.getStatus()
        );
    }
}