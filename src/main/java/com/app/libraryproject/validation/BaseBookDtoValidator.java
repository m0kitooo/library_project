package com.app.libraryproject.validation;

import com.app.libraryproject.dto.book.CreateBookRequest;
import lombok.NonNull;

import java.util.StringJoiner;

import static org.apache.commons.lang3.StringUtils.isBlank;

public abstract class BaseBookDtoValidator {
    private static final String ISBN_REGEX = "^(\\d{10}|\\d{13})$";

    public static String validate(@NonNull CreateBookRequest request) {
        StringJoiner joiner = new StringJoiner("|");

        if (request.isbn() != null && !request.isbn().matches(ISBN_REGEX))
            joiner.add("ISBN has to be 10 or 13 digits length");
        if (isBlank(request.title()))
            joiner.add("Title can't be null or blank");
        if (isBlank(request.author()))
            joiner.add("Author can't be null or blank");
        if (request.publisher() != null && request.publisher().isBlank())
            joiner.add("Publisher can't be blank");
        if (isBlank(request.edition()))
            joiner.add("Edition can't be null or blank");
        if (!isPublicationYearValid(request.publicationYear()))
            joiner.add("Publication year can't be null, less than 0 or greater than current year");

        return joiner.toString();
    }

    private static boolean isPublicationYearValid(Integer publicationYear) {
        return publicationYear != null &&
               publicationYear >= 0 &&
               publicationYear <= java.time.Year.now().getValue();
    }
}
