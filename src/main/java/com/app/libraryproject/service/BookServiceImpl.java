package com.app.libraryproject.service;

import com.app.libraryproject.dto.CreateBookRequest;
import com.app.libraryproject.dto.BookResponse;
import com.app.libraryproject.entity.Book;
import com.app.libraryproject.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<BookResponse> findAll() {
        return bookRepository
                .findAll()
                .stream()
                .map(Book::toBookResponse)
                .toList();
    }

    @Override
    public List<BookResponse> findBooksByTitle(String title) {
        return bookRepository
                .findByTitleContainingIgnoreCase(title)
                .stream()
                .map(Book::toBookResponse)
                .toList();
    }

    @Override
    public BookResponse registerBook(CreateBookRequest book) {
        return bookRepository.save(book.toBook()).toBookResponse();
    }
}
