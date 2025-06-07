package com.app.libraryproject.dto.librarycard;

import com.app.libraryproject.dto.member.CreateMemberRequest;
import com.app.libraryproject.exception.IllegalRequestArgumentException;

import java.time.LocalDate;

public record CreateLibraryCardRequest(
        CreateMemberRequest createMemberRequest,
        LocalDate expiryDate
) {
    public CreateLibraryCardRequest {
        if (createMemberRequest == null || expiryDate == null) {
            throw new IllegalRequestArgumentException("createMemberRequest and expiryDate are required");
        }
    }
}
