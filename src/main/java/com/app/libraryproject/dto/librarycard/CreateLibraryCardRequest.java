package com.app.libraryproject.dto.librarycard;

import com.app.libraryproject.dto.member.CreateMemberRequest;
import com.app.libraryproject.exception.InvalidRequestArgumentException;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record CreateLibraryCardRequest(
        Long memberId,
        LocalDate expiryDate
) {
    public CreateLibraryCardRequest {
        if (memberId == null || expiryDate == null) {
            throw new InvalidRequestArgumentException("(memberId, expiryDate) cannot be null");
        }
    }
}
