package com.app.libraryproject.dto.proposal;

import com.app.libraryproject.exception.IllegalRequestArgumentException;

public record ModifyProposalRequest(
        Long id,
        String title,
        String description
) {
    public ModifyProposalRequest {
        if (id == null || title == null || title.isEmpty()) {
            throw new IllegalRequestArgumentException("(id, title) cannot be null or empty");
        }
    }
}
