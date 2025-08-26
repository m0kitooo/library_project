package com.app.libraryproject.dto.book;

import com.app.libraryproject.entity.Book;
import com.app.libraryproject.exception.InvalidRequestArgumentException;

import static io.micrometer.common.util.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

public record CreateBookRequest(
        String isbn,
        String title,
        String author,
        Integer publicationYear,
        String description,
        Integer quantity
) {
    private static final String ISBN_REGEX = "^(\\d{10}|\\d{13})$";

    public CreateBookRequest {
        if (isbn != null && !isbn.matches(ISBN_REGEX))
            throw new InvalidRequestArgumentException("ISBN has to be 10 or 13 digits length");
        if (isEmpty(title) || !title.equals(title.trim()))
            throw new InvalidRequestArgumentException("Title can't be null or empty and must be trimmed");
        if (isBlank(author))
            throw new InvalidRequestArgumentException("Author can't be null or empty");
        if (quantity != null && quantity < 0)
            throw new InvalidRequestArgumentException("Quantity can't be less than 0");
        if (publicationYear == null || publicationYear < 0)
            throw new InvalidRequestArgumentException("Publication year can't be null or less than 0");
    }

    public Book toBook() {
        return Book
                .builder()
                .isbn(isbn)
                .title(title)
                .author(author)
                .publicationYear(publicationYear)
                .description(description)
                .quantity(quantity)
                .build();
    }
}
