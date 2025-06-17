package com.app.libraryproject.service;

import com.app.libraryproject.dto.disposedbook.DisposedBookResponse;
import com.app.libraryproject.entity.Book;
import com.app.libraryproject.entity.DisposedBook;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.repository.DisposedBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisposedBookServiceImpl implements DisposedBookService {

    private final DisposedBookRepository disposedBookRepository;

    @Override
    @Transactional
    public void addDisposedBook(Book book) {
        Optional<DisposedBook> existingDisposedBookOpt = disposedBookRepository.findByOriginalBookId(book.getId());

        if (existingDisposedBookOpt.isPresent()) {
            DisposedBook existingDisposedBook = existingDisposedBookOpt.get();
            existingDisposedBook.setQuantityToDispose(existingDisposedBook.getQuantityToDispose() + 1);
            disposedBookRepository.save(existingDisposedBook);
        } else {
            DisposedBook newDisposedBook = DisposedBook.builder()
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .description(book.getDescription())
                    .quantityToDispose(1)
                    .dateAdded(LocalDate.now())
                    .originalBook(book)
                    .build();
            disposedBookRepository.save(newDisposedBook);
        }
    }

    @Override
    public List<DisposedBookResponse> getAllDisposedBooks() {
        return disposedBookRepository.findAll().stream()
                .map(DisposedBookResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void markAsUtilized(Long disposedBookId) {
        DisposedBook disposedBook = disposedBookRepository.findById(disposedBookId)
                .orElseThrow(() -> new RecordNotFoundException("Disposed book not found with id: " + disposedBookId));

        int quantity = disposedBook.getQuantityToDispose();

        if (quantity > 1) {
            disposedBook.setQuantityToDispose(quantity - 1);
            disposedBookRepository.save(disposedBook);
        } else {
            disposedBookRepository.delete(disposedBook);
        }
    }

    @Override
    @Transactional
    public void utilizeAll(Long disposedBookId) {
        DisposedBook disposedBook = disposedBookRepository.findById(disposedBookId)
                .orElseThrow(() -> new RecordNotFoundException("Disposed book not found with id: " + disposedBookId));
        disposedBookRepository.delete(disposedBook);
    }
}