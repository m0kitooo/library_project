package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookreturn.BookReturnResponse;
import com.app.libraryproject.dto.bookreturn.CreateBookReturnRequest;

public interface BookReturnService {
    BookReturnResponse returnBook(CreateBookReturnRequest request);
}
