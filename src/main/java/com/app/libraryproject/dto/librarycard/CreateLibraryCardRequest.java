package com.app.libraryproject.dto.librarycard;

import com.app.libraryproject.dto.member.CreateMemberRequest;
import com.app.libraryproject.exception.InvalidRequestArgumentException;

import java.time.LocalDate;

public record CreateLibraryCardRequest(
        CreateMemberRequest createMemberRequest,
        LocalDate expiryDate
) {
    public CreateLibraryCardRequest {
        if (createMemberRequest == null || expiryDate == null) {
            throw new InvalidRequestArgumentException("createMemberRequest and expiryDate are required");
        }
    }
}
