package com.app.libraryproject.service;

import com.app.libraryproject.dto.book.CreateBookRequest;
import com.app.libraryproject.dto.book.BookResponse;
import com.app.libraryproject.dto.book.UpdateBookRequest;
import com.app.libraryproject.entity.Book;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public BookResponse find(Long id) {
        return bookRepository
                .findByIdAndArchivedFalse(id)
                .orElseThrow(() -> new RecordNotFoundException("There is no book with such id"))
                .toBookResponse();
    }

    @Override
    public List<BookResponse> findAll() {
        return bookRepository
                .findByArchivedFalse()
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

    @Override
    @Transactional
    public BookResponse deleteBook(Long id) {
        if (bookRepository.archive(id) == 0) {
            throw new RuntimeException("Couldn't set the book as deleted");
        }

        return bookRepository
                .findById(id)
                .orElseThrow()
                .toBookResponse();
    }

    @Override
    public BookResponse updateBook(UpdateBookRequest updateBookRequest) {
        Book book = bookRepository
                .findById(updateBookRequest.id())
                .orElseThrow(() -> new RecordNotFoundException("Book not found with id: " + updateBookRequest.id()));

        book.setTitle(updateBookRequest.title());
        book.setAuthor(updateBookRequest.author());
        book.setDescription(updateBookRequest.description());
        book.setQuantity(updateBookRequest.quantity());

        return bookRepository.save(book).toBookResponse();
    }
}
