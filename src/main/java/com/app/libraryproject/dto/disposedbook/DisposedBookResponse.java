package com.app.libraryproject.dto.disposedbook;

import com.app.libraryproject.entity.DisposedBook;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record DisposedBookResponse(
        Long id,
        String title,
        String author,
        String description,
        Integer quantityToDispose,
        LocalDate dateAdded,
        Long originalBookId
) {
    public static DisposedBookResponse from(DisposedBook disposedBook) {
        return DisposedBookResponse.builder()
                .id(disposedBook.getId())
                .title(disposedBook.getTitle())
                .author(disposedBook.getAuthor())
                .description(disposedBook.getDescription())
                .quantityToDispose(disposedBook.getQuantityToDispose())
                .dateAdded(disposedBook.getDateAdded())
                .originalBookId(disposedBook.getOriginalBook() != null ? disposedBook.getOriginalBook().getId() : null)
                .build();
    }
}