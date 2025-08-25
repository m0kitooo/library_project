package com.app.libraryproject.exception;

/**
 * Throw when the incoming api request contains invalid data
 */
public class InvalidRequestArgumentException extends RuntimeException {
    public InvalidRequestArgumentException(String message) {
        super(message);
    }
}
