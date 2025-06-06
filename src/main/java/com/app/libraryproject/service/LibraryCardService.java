package com.app.libraryproject.service;

import com.app.libraryproject.dto.CreateLibraryCardRequest;
import com.app.libraryproject.dto.LibraryCardResponse;

public interface LibraryCardService {
    LibraryCardResponse register(CreateLibraryCardRequest createLibraryCardRequest);
}
