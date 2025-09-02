package com.app.libraryproject.dto.librarycard;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record LibraryCardResponse(
        Long id,
        Long memberId,
        LocalDate expiryDate
) {
    public static LibraryCardResponse from(com.app.libraryproject.entity.LibraryCard libraryCard) {
        return LibraryCardResponse
                .builder()
                .id(libraryCard.getId())
                .memberId(libraryCard.getMember().getId())
                .expiryDate(libraryCard.getExpiryDate())
                .build();
    }
}
