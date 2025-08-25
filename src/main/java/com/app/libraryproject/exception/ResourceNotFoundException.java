package com.app.libraryproject.exception;

import org.springframework.http.HttpStatus;
import com.app.libraryproject.model.error.AppError;

public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(AppError error) {
        super(error, HttpStatus.NOT_FOUND);
    }
}