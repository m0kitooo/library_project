package com.app.libraryproject.dto.proposal;

import com.app.libraryproject.exception.InvalidRequestArgumentException;

public record SendProposalRequest (
    String title,
    String description,
    String proposedBy
) {
    public SendProposalRequest {
        if (title == null || title.isEmpty()) {
            throw new InvalidRequestArgumentException("(title) cannot be null or empty)");
        }
    }
}
