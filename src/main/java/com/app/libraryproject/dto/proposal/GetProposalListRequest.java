package com.app.libraryproject.dto.proposal;

public record GetProposalListRequest(
        Integer status,
        Integer page,
        Integer limit
) {
    public GetProposalListRequest {
        if (page == null || limit == null) {
            throw new IllegalArgumentException("Page and limit cannot be null");
        }
    }
}
