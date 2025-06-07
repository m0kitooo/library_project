package com.app.libraryproject.dto.book;

import com.app.libraryproject.entity.Book;

public record CreateBookRequest(
        String title,
        String author,
        String description,
        Integer quantity
) {
    public Book toBook() {
        return Book
                .builder()
                .title(title)
                .author(author)
                .description(description)
                .quantity(quantity)
                .build();
    }
}
