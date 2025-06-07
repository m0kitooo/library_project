package com.app.libraryproject.dto.proposal;

import com.app.libraryproject.exception.InvalidResponseArgumentException;
import lombok.Builder;

public record GetProposalDetailsResponse(
        String title,
        String description,
        String status,
        String proposedBy
) {
    @Builder
    public GetProposalDetailsResponse {
        if (title == null || status == null || proposedBy == null) {
            throw new InvalidResponseArgumentException("(title, status, proposedBy) cannot be null");
        }
    }
}
