package com.app.libraryproject.dto.proposal;

import com.app.libraryproject.exception.InvalidResponseArgumentException;
import lombok.Builder;

@Builder
public record GetProposalDetailsResponse(
        long id,
        String title,
        String description,
        String status,
        String proposedBy
) {}
