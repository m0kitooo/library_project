package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarycard.CreateLibraryCardRequest;
import com.app.libraryproject.dto.librarycard.GetLibraryCardDetailsResponse;
import com.app.libraryproject.dto.librarycard.LibraryCardResponse;
import com.app.libraryproject.entity.LibraryCard;

public interface LibraryCardService {
    LibraryCardResponse registerLibraryCard(CreateLibraryCardRequest createLibraryCardRequest);
    GetLibraryCardDetailsResponse getLibraryCardDetails(Long libraryCardId);
    LibraryCard getLibraryCardById(Long libraryCardId);
}