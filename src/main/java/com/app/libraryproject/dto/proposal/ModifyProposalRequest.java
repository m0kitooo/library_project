package com.app.libraryproject.dto.proposal;

import com.app.libraryproject.exception.InvalidRequestArgumentException;
import com.app.libraryproject.model.PlanType;

public record ModifyProposalRequest(
        Long id,
        String title,
        String description,
        PlanType type
) {
    public ModifyProposalRequest {
        if (id == null || title == null || title.isEmpty()) {
            throw new InvalidRequestArgumentException("(id, title) cannot be null or empty");
        }
    }
}
