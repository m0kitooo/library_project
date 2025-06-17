package com.app.libraryproject.service;

import com.app.libraryproject.dto.book.CreateBookRequest;
import com.app.libraryproject.dto.book.BookResponse;
import com.app.libraryproject.dto.book.UpdateBookRequest;
import com.app.libraryproject.entity.Book;

import java.util.List;

public interface BookService {
    BookResponse find(Long id);
    List<BookResponse> findAll();
    List<BookResponse> findBooksByTitle(String title);
    BookResponse registerBook(CreateBookRequest book);
    BookResponse deleteBook(Long id);
    BookResponse updateBook(UpdateBookRequest book);
    List<Book> getBookByAuthor(String author);
}
