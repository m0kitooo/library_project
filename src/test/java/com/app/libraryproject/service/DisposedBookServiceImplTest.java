package com.app.libraryproject.service;

import com.app.libraryproject.dto.disposedbook.DisposedBookResponse;
import com.app.libraryproject.entity.Book;
import com.app.libraryproject.entity.DisposedBook;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.repository.DisposedBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DisposedBookServiceImplTest {

    @Mock
    private DisposedBookRepository disposedBookRepository;

    @InjectMocks
    private DisposedBookServiceImpl disposedBookService;

    private Book book;
    private DisposedBook disposedBook;

    @BeforeEach
    void setUp() {
        book = Book.builder()
                .id(1L)
                .title("Stara książka")
                .author("Autor")
                .description("Opis")
                .build();

        disposedBook = DisposedBook.builder()
                .id(1L)
                .originalBook(book)
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .quantityToDispose(1)
                .dateAdded(LocalDate.now())
                .build();
    }

    @Test
    void addDisposedBook_whenBookIsNewToDisposal_shouldCreateNewRecord() {
        // Given
        when(disposedBookRepository.findByOriginalBookId(book.getId())).thenReturn(Optional.empty());

        // When
        disposedBookService.addDisposedBook(book);

        // Then
        ArgumentCaptor<DisposedBook> disposedBookCaptor = ArgumentCaptor.forClass(DisposedBook.class);
        verify(disposedBookRepository).save(disposedBookCaptor.capture());

        DisposedBook savedBook = disposedBookCaptor.getValue();
        assertThat(savedBook.getOriginalBook()).isEqualTo(book);
        assertThat(savedBook.getTitle()).isEqualTo(book.getTitle());
        assertThat(savedBook.getQuantityToDispose()).isEqualTo(1);
    }

    @Test
    void addDisposedBook_whenBookAlreadyInDisposal_shouldIncrementQuantity() {
        // Given
        when(disposedBookRepository.findByOriginalBookId(book.getId())).thenReturn(Optional.of(disposedBook));
        int initialQuantity = disposedBook.getQuantityToDispose();

        // When
        disposedBookService.addDisposedBook(book);

        // Then
        ArgumentCaptor<DisposedBook> disposedBookCaptor = ArgumentCaptor.forClass(DisposedBook.class);
        verify(disposedBookRepository).save(disposedBookCaptor.capture());

        DisposedBook savedBook = disposedBookCaptor.getValue();
        assertThat(savedBook.getQuantityToDispose()).isEqualTo(initialQuantity + 1);
    }

    @Test
    void getAllDisposedBooks_shouldReturnListOfResponses() {
        // Given
        when(disposedBookRepository.findAll()).thenReturn(List.of(disposedBook));

        // When
        List<DisposedBookResponse> responses = disposedBookService.getAllDisposedBooks();

        // Then
        assertThat(responses).hasSize(1);
        DisposedBookResponse response = responses.get(0);
        assertThat(response.id()).isEqualTo(disposedBook.getId());
        assertThat(response.title()).isEqualTo(disposedBook.getTitle());
        assertThat(response.originalBookId()).isEqualTo(book.getId());
    }

    @Test
    void markAsUtilized_whenQuantityIsGreaterThanOne_shouldDecrementQuantity() {
        // Given
        disposedBook.setQuantityToDispose(5);
        when(disposedBookRepository.findById(1L)).thenReturn(Optional.of(disposedBook));

        // When
        disposedBookService.markAsUtilized(1L);

        // Then
        verify(disposedBookRepository).save(disposedBook);
        assertThat(disposedBook.getQuantityToDispose()).isEqualTo(4);
        verify(disposedBookRepository, never()).delete(any(DisposedBook.class));
    }

    @Test
    void markAsUtilized_whenQuantityIsOne_shouldDeleteRecord() {
        // Given
        disposedBook.setQuantityToDispose(1);
        when(disposedBookRepository.findById(1L)).thenReturn(Optional.of(disposedBook));

        // When
        disposedBookService.markAsUtilized(1L);

        // Then
        verify(disposedBookRepository).delete(disposedBook);
        verify(disposedBookRepository, never()).save(any(DisposedBook.class));
    }

    @Test
    void markAsUtilized_whenBookNotFound_shouldThrowException() {
        // Given
        when(disposedBookRepository.findById(99L)).thenReturn(Optional.empty());

        // When & Then
        RecordNotFoundException exception = assertThrows(RecordNotFoundException.class,
                () -> disposedBookService.markAsUtilized(99L));
        assertThat(exception.getMessage()).isEqualTo("Disposed book not found with id: 99");
    }

    @Test
    void utilizeAll_shouldDeleteRecord() {
        // Given
        when(disposedBookRepository.findById(1L)).thenReturn(Optional.of(disposedBook));

        // When
        disposedBookService.utilizeAll(1L);

        // Then
        verify(disposedBookRepository).delete(disposedBook);
    }

    @Test
    void utilizeAll_whenBookNotFound_shouldThrowException() {
        // Given
        when(disposedBookRepository.findById(99L)).thenReturn(Optional.empty());

        // When & Then
        RecordNotFoundException exception = assertThrows(RecordNotFoundException.class,
                () -> disposedBookService.utilizeAll(99L));
        assertThat(exception.getMessage()).isEqualTo("Disposed book not found with id: 99");
    }
}