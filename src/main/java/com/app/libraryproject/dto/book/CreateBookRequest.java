package com.app.libraryproject.dto.book;

import com.app.libraryproject.entity.Book;
import com.app.libraryproject.exception.InvalidRequestArgumentException;
import com.app.libraryproject.validation.BaseBookDtoValidator;

import static io.micrometer.common.util.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

public record CreateBookRequest(
        String isbn,
        String title,
        String author,
        String publisher,
        String edition,
        Integer publicationYear
) implements BaseBookDto {
    private static final String ISBN_REGEX = "^(\\d{10}|\\d{13})$";

    public CreateBookRequest {
        String validationErrors = BaseBookDtoValidator.validate(this);
        if (!isEmpty(validationErrors)) {
            throw new InvalidRequestArgumentException(validationErrors);
        }
    }

    public Book toBook() {
        return Book
                .builder()
                .isbn(isbn)
                .title(title)
                .author(author)
                .publicationYear(publicationYear)
                .build();
    }
}
