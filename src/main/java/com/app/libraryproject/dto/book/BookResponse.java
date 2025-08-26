package com.app.libraryproject.dto.book;

import com.app.libraryproject.entity.Book;
import lombok.Builder;

@Builder
public record BookResponse(
        Long id,
        String isbn,
        String title,
        String author,
        Integer publishedYear,
        String description,
        Integer quantity
) {
    public static BookResponse from(Book book) {
        return BookResponse
                .builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publishedYear(book.getPublicationYear())
                .description(book.getDescription())
                .quantity(book.getQuantity())
                .build();
    }
}
