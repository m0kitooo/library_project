package com.app.libraryproject.dto.user;

import com.app.libraryproject.exception.InvalidRequestArgumentException;

public record GetUserListRequest(
        Integer page,
        Integer limit
) {
    public GetUserListRequest {
        if (page == null || limit == null) {
            throw new InvalidRequestArgumentException("(page, limit) cannot be null");
        }
    }
}
