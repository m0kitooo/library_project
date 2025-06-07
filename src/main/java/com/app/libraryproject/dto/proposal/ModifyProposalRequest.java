package com.app.libraryproject.dto.proposal;

public record ModifyProposalRequest (
    Long id,
    String title,
    String description
) {
    public ModifyProposalRequest {
        if (id == null || title.isEmpty()) {
            throw new IllegalArgumentException("Id and title are required");
        }
    }
}
