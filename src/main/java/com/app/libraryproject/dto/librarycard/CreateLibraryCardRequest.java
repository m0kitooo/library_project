package com.app.libraryproject.dto.librarycard;

import com.app.libraryproject.dto.member.CreateMemberRequest;
import com.app.libraryproject.exception.InvalidRequestArgumentException;

public record CreateLibraryCardRequest(
        CreateMemberRequest createMemberRequest
) {
    public CreateLibraryCardRequest {
        if (createMemberRequest == null)
            throw new InvalidRequestArgumentException("Member can't be null");
    }
}
