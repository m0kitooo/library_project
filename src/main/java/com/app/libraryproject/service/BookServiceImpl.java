package com.app.libraryproject.service;

import com.app.libraryproject.dto.BookDto;
import com.app.libraryproject.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public BookDto registerBook(BookDto book) {
        return bookRepository.save(book.toBook()).toBookDto();
    }
}
