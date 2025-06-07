package com.app.libraryproject.dto.book;

import com.app.libraryproject.exception.InvalidRequestArgumentException;
import lombok.Builder;

import static io.micrometer.common.util.StringUtils.isBlank;

@Builder
public record UpdateBookRequest(
        Long id,
        String title,
        String author,
        String description,
        Integer quantity
) {
    public UpdateBookRequest {
        if (id == null)
            throw new InvalidRequestArgumentException("Id can't be null");
        if (isBlank(title))
            throw new InvalidRequestArgumentException("Title can't be null or blank");
        if (quantity < 0)
            throw new InvalidRequestArgumentException("Quantity can't be less than 0");
    }
}
