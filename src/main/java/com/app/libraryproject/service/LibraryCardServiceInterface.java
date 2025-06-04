package com.app.libraryproject.service;

import com.app.libraryproject.dto.CreateLibraryCardRequest;
import com.app.libraryproject.entity.LibraryCard;

public interface LibraryCardServiceInterface {
    LibraryCard registerCard(CreateLibraryCardRequest createMemberRequest);
}
