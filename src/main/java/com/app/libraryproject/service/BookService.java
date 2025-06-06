package com.app.libraryproject.service;

import com.app.libraryproject.dto.CreateBookRequest;
import com.app.libraryproject.dto.BookResponse;
import com.app.libraryproject.dto.UpdateBookRequest;

import java.util.List;

public interface BookService {
    List<BookResponse> findAll();
    List<BookResponse> findBooksByTitle(String title);
    BookResponse registerBook(CreateBookRequest book);
    BookResponse deleteBook(Long id);
    BookResponse updateBook(UpdateBookRequest book);
}
