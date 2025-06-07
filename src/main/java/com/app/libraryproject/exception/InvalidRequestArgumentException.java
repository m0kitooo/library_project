package com.app.libraryproject.exception;

public class InvalidRequestArgumentException extends RuntimeException {
    public InvalidRequestArgumentException(String message) {
        super(message);
    }
}
