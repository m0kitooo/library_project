package com.app.libraryproject.dto.proposal;

import com.app.libraryproject.exception.InvalidRequestArgumentException;
import com.app.libraryproject.model.PlanType;

public record SendProposalRequest (
    String title,
    String description,
    String proposedBy,
    PlanType type
) {
    public SendProposalRequest {
        if (title == null || title.isEmpty()) {
            throw new InvalidRequestArgumentException("(title) cannot be null or empty)");
        }
    }
}
