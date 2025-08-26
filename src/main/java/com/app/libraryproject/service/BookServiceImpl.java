package com.app.libraryproject.service;

import com.app.libraryproject.dto.book.CreateBookRequest;
import com.app.libraryproject.dto.book.BookResponse;
import com.app.libraryproject.dto.book.UpdateBookRequest;
import com.app.libraryproject.entity.Book;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.exception.ResourceConflictException;
import com.app.libraryproject.exception.ResourceNotFoundException;
import com.app.libraryproject.model.error.AppError;
import com.app.libraryproject.model.error.ErrorCode;
import com.app.libraryproject.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.app.libraryproject.model.error.ErrorCode.BOOK_HAS_ACTIVE_LOANS;
import static com.app.libraryproject.model.error.ErrorCode.BOOK_NOT_FOUND;

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
                .findByTitleContainingIgnoreCaseAndArchivedFalse(title)
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
        Book bookToDelete = bookRepository
                .findByIdAndArchivedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(new AppError(BOOK_NOT_FOUND, "Book not found with id: " + id)));

        if (!bookToDelete.getBookLoans().isEmpty())
            throw new ResourceConflictException(new AppError(BOOK_HAS_ACTIVE_LOANS, "Book can't be deleted, because it has active loans"));

        bookToDelete.setArchived(true);
        bookRepository.save(bookToDelete);

        log.info("Book with id {} archived", id);
        return bookToDelete.toBookResponse();
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
