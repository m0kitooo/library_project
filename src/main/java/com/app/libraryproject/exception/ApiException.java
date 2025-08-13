package com.app.libraryproject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import com.app.libraryproject.model.error.AppError;

@Getter
public abstract class ApiException extends RuntimeException {
    private final AppError error;
    private final HttpStatus status;

    public ApiException(AppError error, HttpStatus status) {
        super(error.message());
        this.error = error;
        this.status = status;
    }
}
