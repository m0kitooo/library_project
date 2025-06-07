package com.app.libraryproject.dto.librarycard;

import com.app.libraryproject.dto.member.CreateMemberRequest;

public record CreateLibraryCardRequest(
        CreateMemberRequest createMemberRequest
) { }
