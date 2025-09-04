package com.app.libraryproject.dto.book;

import com.app.libraryproject.entity.Book;
import com.app.libraryproject.exception.InvalidRequestArgumentException;
import com.app.libraryproject.validation.BaseBookDtoValidator;

import static io.micrometer.common.util.StringUtils.isEmpty;

public record CreateBookRequest(
        String isbn,
        String title,
        String author,
        String callNumber,
        String publisher,
        String edition,
        Integer publicationYear
) {
    private static final String ISBN_REGEX = "^(\\d{10}|\\d{13})$";

    public CreateBookRequest {
//        String validationErrors = BaseBookDtoValidator.validate(this);
//        if (!isEmpty(validationErrors)) {
//            throw new InvalidRequestArgumentException(validationErrors);
//        }

        if (isbn != null && !isbn.matches(ISBN_REGEX))
            throw new InvalidRequestArgumentException("ISBN has to be 10 or 13 digits length");
        if (title == null || title.isBlank())
            throw new InvalidRequestArgumentException("Title can't be null or blank");
        if (author == null || author.isBlank())
            throw new InvalidRequestArgumentException("Author can't be null or blank");
        if (callNumber == null || callNumber.isBlank())
            throw new InvalidRequestArgumentException("Call number can't be null or blank");
        if (publisher != null && publisher.isBlank())
            throw new InvalidRequestArgumentException("Publisher can't be blank");
        if (edition == null || edition.isBlank())
            throw new InvalidRequestArgumentException("Edition can't be null or blank");
        if (publicationYear == null ||
                publicationYear < 0 ||
                publicationYear > java.time.Year.now().getValue())
            throw new InvalidRequestArgumentException("Publication year can't be null, less than 0 or greater than current year");
    }

    public Book toBook() {
        return Book
                .builder()
                .isbn(isbn)
                .title(title)
                .author(author)
                .callNumber(callNumber)
                .publisher(publisher)
                .edition(edition)
                .publicationYear(publicationYear)
                .build();
    }
}
