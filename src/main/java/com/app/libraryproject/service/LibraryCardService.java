package com.app.libraryproject.service;

import com.app.libraryproject.dto.librarycard.CreateLibraryCardRequest;
import com.app.libraryproject.dto.librarycard.LibraryCardResponse;

public interface LibraryCardService {
    LibraryCardResponse register(CreateLibraryCardRequest createLibraryCardRequest);
}
