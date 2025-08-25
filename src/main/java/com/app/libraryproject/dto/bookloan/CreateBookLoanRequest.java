package com.app.libraryproject.dto.bookloan;

import com.app.libraryproject.exception.InvalidRequestArgumentException;

public record CreateBookLoanRequest(
        Long bookId,
        Long memberId
) {
    public CreateBookLoanRequest {
        if (bookId == null || memberId == null) {
            throw new InvalidRequestArgumentException("Both bookId and memberId cannot be null");
        }
    }
}
