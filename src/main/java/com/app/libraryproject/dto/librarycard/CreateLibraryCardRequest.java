package com.app.libraryproject.dto.librarycard;

import com.app.libraryproject.dto.member.CreateMemberRequest;
import com.app.libraryproject.exception.InvalidRequestArgumentException;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record CreateLibraryCardRequest(
        @JsonProperty("member") CreateMemberRequest createMemberRequest,
        LocalDate expiryDate
) {
    public CreateLibraryCardRequest {
        if (createMemberRequest == null || expiryDate == null) {
            throw new InvalidRequestArgumentException("createMemberRequest and expiryDate are required");
        }
    }
}
