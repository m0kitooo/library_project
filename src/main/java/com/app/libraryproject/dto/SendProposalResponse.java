package com.app.libraryproject.dto;

public record SendProposalResponse (
    Long id,
    String title,
    String description
) {}