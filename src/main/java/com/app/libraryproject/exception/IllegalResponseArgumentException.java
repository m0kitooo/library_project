package com.app.libraryproject.exception;

public class IllegalResponseArgumentException extends RuntimeException { //wyjątek do zakomunikowania, że backend próbuje wysłać nie prawidłowe dane
    public IllegalResponseArgumentException(String message) {
        super(message);
    }
}
