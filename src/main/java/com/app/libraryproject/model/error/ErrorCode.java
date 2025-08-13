package com.app.libraryproject.model.error;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
    BOOK_NOT_FOUND("BOOK_001"),
    BOOK_LOAN_NOT_FOUND("BOOK_LOAN_001"),
    MEMBER_NOT_FOUND("MEMBER_001");

    private final String code;
    // private final String defaultMessage;
    
    @JsonValue
    public String getCode() {
        return code;
    }

    ErrorCode(String code) {
        this.code = code;
    }

    // ErrorCode(String code, String defaultMessage) {
    //     this.code = code;
    //     this.defaultMessage = defaultMessage;
    // }
}
