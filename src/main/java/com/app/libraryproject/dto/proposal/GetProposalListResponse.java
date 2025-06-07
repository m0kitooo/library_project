package com.app.libraryproject.dto.proposal;

import com.app.libraryproject.exception.InvalidResponseArgumentException;
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
        public ProposalListItem {
            if (id == null || title == null || status == null || proposedBy == null) {
                throw new InvalidResponseArgumentException("(id, title, status, proposedBy) cannot be null)");
            }
        }
    }
}
