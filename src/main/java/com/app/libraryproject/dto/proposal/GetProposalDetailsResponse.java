package com.app.libraryproject.dto.proposal;

import lombok.Builder;

public record GetProposalDetailsResponse(
        String title,
        String description,
        String status,
        String proposedBy
) {
    @Builder
    public GetProposalDetailsResponse {}
}
