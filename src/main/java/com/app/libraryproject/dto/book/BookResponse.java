package com.app.libraryproject.dto.book;

import com.app.libraryproject.entity.Book;
import lombok.Builder;

@Builder
public record BookResponse(
        Long id,
        String title,
        String author,
        String description,
        Integer quantity
) {
    public static BookResponse from(Book book) {
        return BookResponse
                .builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .quantity(book.getQuantity())
                .build();
    }
}
