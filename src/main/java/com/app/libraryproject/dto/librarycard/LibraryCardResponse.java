package com.app.libraryproject.dto.librarycard;

import lombok.*;

import java.time.LocalDate;

public record LibraryCardResponse (
    Long id,
    LocalDate expiryDate
) {
    @Builder
    public LibraryCardResponse {
        if (id == null || expiryDate != null) {
            throw new IllegalArgumentException("Id and expiryDate are required");
        }
    }
}
