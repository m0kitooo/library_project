package com.app.libraryproject.dto;

import com.app.libraryproject.entity.Book;

public record CreateBookRequest(
        String title,
        String description
) {
    public Book toBook() {
        return Book
                .builder()
                .title(title)
                .description(description)
                .build();
    }
}
