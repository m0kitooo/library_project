package com.app.libraryproject.exception;

import com.app.libraryproject.model.error.AppError;
import org.springframework.http.HttpStatus;

// The goal is to serve same purpose as <InvalidRequestArgumentException> class and not break existing code as it contains more data

public class InvalidRequestException extends ApiException {
    public InvalidRequestException(AppError error) {
        super(error, HttpStatus.BAD_REQUEST);
    }
}
