package com.app.libraryproject.dto;

public record SendProposalRequest (
    String title,
    String description,
    String proposedBy
) {}
