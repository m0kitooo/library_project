package com.app.libraryproject.service;

import com.app.libraryproject.dto.LibraryCardResponse;
import com.app.libraryproject.dto.CreateMemberRequest;

public interface LibraryCardService {
    LibraryCardResponse register(CreateMemberRequest createMemberRequest);
}
