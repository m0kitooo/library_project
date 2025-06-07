package com.app.libraryproject.exception;

public class InvalidRequestArgumentException extends RuntimeException { //wyjątek do zakomunikowania, że zostały wprowadzone błędne dane z frontendu
    public InvalidRequestArgumentException(String message) {
        super(message);
    }
}
