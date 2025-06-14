package com.app.libraryproject.dto.librarycard;

import com.app.libraryproject.dto.member.MemberResponse;
import com.app.libraryproject.entity.LibraryCard;
import com.app.libraryproject.exception.InvalidResponseArgumentException;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record LibraryCardResponse(
        Long libraryCardId,
        Long memberId,
        LocalDate expiryDate
) {
    public LibraryCardResponse {
        if (libraryCardId == null || memberId == null || expiryDate == null) {
            throw new InvalidResponseArgumentException("(libraryCardId, memberId, expiryDate) cannot be null");
        }
    }

    public static LibraryCardResponse from(LibraryCard libraryCard) {
        return LibraryCardResponse
                .builder()
                .libraryCardId(libraryCard.getId())
                .memberId(libraryCard.getMember().getId())
                .expiryDate(libraryCard.getExpiryDate())
                .build();
    }
}
