package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarycard.CreateLibraryCardRequest;
import com.app.libraryproject.dto.librarycard.GetLibraryCardDetailsResponse;

public interface LibraryCardService {
    void registerLibraryCard(CreateLibraryCardRequest createLibraryCardRequest);
    GetLibraryCardDetailsResponse getLibraryCardDetails(Long libraryCardId);
}