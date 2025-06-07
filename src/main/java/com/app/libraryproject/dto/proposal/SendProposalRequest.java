package com.app.libraryproject.dto.proposal;

import com.app.libraryproject.exception.IllegalRequestArgumentException;

public record SendProposalRequest (
    String title,
    String description,
    String proposedBy
) {
    public SendProposalRequest {
        if (title == null || title.isEmpty()) {
            throw new IllegalRequestArgumentException("(title) cannot be null or empty)");
        }
    }
}
