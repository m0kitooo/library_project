package com.app.libraryproject.dto.user;

import com.app.libraryproject.exception.InvalidResponseArgumentException;
import lombok.Builder;

import java.util.List;

public record GetPersonListResponse(
    List<PersonResponse> userList
) {
    public record PersonResponse (
            Long id,
            String name,
            String surname
    ) {
        @Builder
        public PersonResponse {
            if (id == null || name == null || name.isEmpty() || surname == null || surname.isEmpty()) {
                throw new InvalidResponseArgumentException("(id, name, surname) cannot be null or empty)");
            }
        }
    }
}
