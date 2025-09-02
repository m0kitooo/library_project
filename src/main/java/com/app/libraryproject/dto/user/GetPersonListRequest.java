package com.app.libraryproject.dto.user;

import com.app.libraryproject.exception.InvalidRequestArgumentException;

public record GetPersonListRequest(
        Integer page,
        Integer limit,
        String filterFullname
) {
    public GetPersonListRequest {
        if (page == null || limit == null) {
            throw new InvalidRequestArgumentException("(page, limit) cannot be null");
        }
    }
}
