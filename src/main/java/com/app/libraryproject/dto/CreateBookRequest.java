package com.app.libraryproject.dto;

import com.app.libraryproject.entity.Book;

public record CreateBookRequest(
        String title,
        String description,
        Integer quantity
) {
    public Book toBook() {
        return Book
                .builder()
                .title(title)
                .description(description)
                .quantity(quantity)
                .build();
    }
}
