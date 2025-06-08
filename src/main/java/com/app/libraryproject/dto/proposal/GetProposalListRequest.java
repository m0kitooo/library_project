package com.app.libraryproject.dto.proposal;

import com.app.libraryproject.exception.InvalidRequestArgumentException;

public record GetProposalListRequest(
        Integer status,
        Integer page,
        Integer limit
) {
    public GetProposalListRequest {
        if (page == null || limit == null) {
            throw new InvalidRequestArgumentException("(page, limit) cannot be null");
        }
    }
}
