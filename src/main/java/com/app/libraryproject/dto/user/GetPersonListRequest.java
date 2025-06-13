package com.app.libraryproject.dto.user;

import com.app.libraryproject.exception.InvalidRequestArgumentException;

public record GetPersonListRequest(
        Integer page,
        Integer limit,
        String filterFullname
        //można tutaj w przyszłości dodać opcje filtrowania i je zaimplementować
) {
    public GetPersonListRequest {
        if (page == null || limit == null) {
            throw new InvalidRequestArgumentException("(page, limit) cannot be null");
        }
    }
}
