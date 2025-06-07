package com.app.libraryproject.exception;

public class IllegalRequestArgumentException extends RuntimeException { //wyjątek do zakomunikowania, że zostały wprowadzone błędne dane z frontendu
    public IllegalRequestArgumentException(String message) {
        super(message);
    }
}
