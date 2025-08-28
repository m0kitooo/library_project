package com.app.libraryproject.dto.book;

import com.app.libraryproject.entity.Book;
import com.app.libraryproject.exception.InvalidRequestArgumentException;
import com.app.libraryproject.validation.BaseBookDtoValidator;

import static io.micrometer.common.util.StringUtils.isEmpty;

public record CreateBookRequest(
        String isbn,
        String title,
        String author,
        String publisher,
        String edition,
        Integer publicationYear
) implements BaseBookDto {
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
                .publisher(publisher)
                .edition(edition)
                .publicationYear(publicationYear)
                .build();
    }
}
