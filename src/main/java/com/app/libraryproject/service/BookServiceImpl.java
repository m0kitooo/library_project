package com.app.libraryproject.service;

import com.app.libraryproject.dto.CreateBookRequest;
import com.app.libraryproject.dto.BookResponse;
import com.app.libraryproject.entity.Book;
import com.app.libraryproject.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
        Book b = bookRepository.save(book.toBook());
        log.info("Book registered: {}", b);
        return b.toBookResponse();
    }
}
