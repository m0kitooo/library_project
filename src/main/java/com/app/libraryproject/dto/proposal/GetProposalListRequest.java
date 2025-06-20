package com.app.libraryproject.dto.proposal;

import com.app.libraryproject.exception.InvalidRequestArgumentException;
import com.app.libraryproject.model.ProposalStatus;

public record GetProposalListRequest(
        ProposalStatus status,
        Integer page,
        Integer limit
) {
    public GetProposalListRequest {
        if (page == null || limit == null) {
            throw new InvalidRequestArgumentException("(page, limit) cannot be null");
        }

        if (status == null) {
            status = ProposalStatus.PENDING;
        }
    }
}
