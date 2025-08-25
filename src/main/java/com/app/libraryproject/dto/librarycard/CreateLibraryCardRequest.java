package com.app.libraryproject.dto.librarycard;

import com.app.libraryproject.exception.InvalidRequestArgumentException;

import java.time.LocalDate;

public record CreateLibraryCardRequest(
        Long memberId,
        LocalDate expiryDate
) {
    public CreateLibraryCardRequest {
        if (memberId == null || expiryDate == null) {
            throw new InvalidRequestArgumentException("Member id and expiryDate are required");
        }
    }
}
