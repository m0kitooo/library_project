package com.app.libraryproject.dto.book;

import com.app.libraryproject.exception.InvalidRequestArgumentException;
import com.app.libraryproject.validation.BaseBookDtoValidator;
import lombok.Builder;

import static io.micrometer.common.util.StringUtils.isBlank;
import static io.micrometer.common.util.StringUtils.isEmpty;

@Builder
public record UpdateBookRequest(
        Long id,
        String isbn,
        String title,
        String author,
        String publisher,
        String edition,
        Integer publicationYear
) {
    public UpdateBookRequest {
//        if (id == null)
//            throw new InvalidRequestArgumentException("Id can't be null");
//        String validationErrors = BaseBookDtoValidator.validate(this);
//        if (!isEmpty(validationErrors)) {
//            throw new InvalidRequestArgumentException(validationErrors);
//        }
    }
}
