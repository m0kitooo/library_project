package com.app.libraryproject.dto.proposal;

import com.app.libraryproject.exception.InvalidRequestArgumentException;
import com.app.libraryproject.model.ProposalStatus;

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
