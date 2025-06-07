package com.app.libraryproject.dto.librarycard;

import com.app.libraryproject.exception.InvalidResponseArgumentException;
import lombok.Builder;

import java.time.LocalDate;

public record GetLibraryCardDetailsResponse(
        Long cardId,
        Long memberId,
        LocalDate expiryDate
) {
    @Builder
    public GetLibraryCardDetailsResponse {
        if (cardId == null || memberId == null || expiryDate == null) {
            throw new InvalidResponseArgumentException("cardId, memberId and expiryDate are required");
        }
    }
}
