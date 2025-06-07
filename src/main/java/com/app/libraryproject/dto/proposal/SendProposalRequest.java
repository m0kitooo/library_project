package com.app.libraryproject.dto.proposal;

public record SendProposalRequest (
    String title,
    String description,
    String proposedBy
) {}
