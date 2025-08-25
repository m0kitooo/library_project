package com.app.libraryproject.exception;

public class RecordConflictException extends RuntimeException {
    public RecordConflictException(String message) {
        super(message);
    }
}
