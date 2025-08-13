package com.app.libraryproject.exception;

import com.app.libraryproject.model.error.AppError;

public class ResourceNotFoundException extends RuntimeException {
    private final AppError error;

    public ResourceNotFoundException(AppError error) {
        super(error.message());
        this.error = error;
    }
}