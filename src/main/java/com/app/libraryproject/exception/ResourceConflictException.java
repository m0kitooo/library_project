package com.app.libraryproject.exception;

import org.springframework.http.HttpStatus;

import com.app.libraryproject.model.error.AppError;

public class ResourceConflictException extends ApiException {
    public ResourceConflictException(AppError error) {
        super(error, HttpStatus.CONFLICT);
    }
}
