package com.app.libraryproject.dto.book;

import com.app.libraryproject.entity.Book;
import com.app.libraryproject.exception.InvalidRequestArgumentException;
import org.apache.commons.lang3.StringUtils;

import static io.micrometer.common.util.StringUtils.isEmpty;

public record CreateBookRequest(
        String title,
        String author,
        String description,
        Integer quantity
) {
    public CreateBookRequest {
        if (isEmpty(title) && !title.equals(title.trim()))
            throw new InvalidRequestArgumentException("Title can't be null or empty and must be trimmed");
        if (quantity < 0)
            throw new InvalidRequestArgumentException("Quantity can't be less than 0");
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
