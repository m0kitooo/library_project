package com.app.libraryproject.dto.proposal;

import lombok.Builder;

import java.util.List;

public record GetProposalListResponse(
    List<ProposalListItem> proposals
) {
    public record ProposalListItem(
            Long id,
            String title,
            String description,
            String status,
            String proposedBy
    ) {
        @Builder
        public ProposalListItem {}
    }
}
