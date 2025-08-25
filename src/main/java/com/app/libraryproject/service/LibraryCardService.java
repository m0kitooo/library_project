package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarycard.CreateLibraryCardRequest;
import com.app.libraryproject.dto.librarycard.GetLibraryCardDetailsResponse;
import com.app.libraryproject.dto.librarycard.LibraryCardResponse;

public interface LibraryCardService {
    LibraryCardResponse registerLibraryCard(CreateLibraryCardRequest createLibraryCardRequest);
    GetLibraryCardDetailsResponse getLibraryCardDetails(Long libraryCardId);
    LibraryCardResponse getActiveLibraryCardByMemberId(Long memberId);
}