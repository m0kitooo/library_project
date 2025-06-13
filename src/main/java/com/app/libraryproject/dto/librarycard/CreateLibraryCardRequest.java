package com.app.libraryproject.dto.librarycard;

import com.app.libraryproject.dto.member.MemberResponse;
import com.app.libraryproject.exception.InvalidRequestArgumentException;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record CreateLibraryCardRequest(
        @JsonProperty("member") MemberResponse memberResponse,
        LocalDate expiryDate
) {
    public CreateLibraryCardRequest {
        if (memberResponse == null || expiryDate == null) {
            throw new InvalidRequestArgumentException("createMemberRequest and expiryDate are required");
        }
    }
}
