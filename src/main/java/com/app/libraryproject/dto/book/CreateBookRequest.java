package com.app.libraryproject.dto.book;

import com.app.libraryproject.entity.Book;
import com.app.libraryproject.exception.InvalidRequestArgumentException;

public record CreateBookRequest(
        String title,
        String author,
        String description,
        Integer quantity
) {
    public CreateBookRequest {
        if (title == null)
            throw new InvalidRequestArgumentException("Title can't be null");
    }

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
