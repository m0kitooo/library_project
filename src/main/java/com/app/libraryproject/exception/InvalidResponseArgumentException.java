package com.app.libraryproject.exception;

public class InvalidResponseArgumentException extends RuntimeException { //wyjątek do zakomunikowania, że backend próbuje wysłać nie prawidłowe dane
    public InvalidResponseArgumentException(String message) {
        super(message);
    }
}
