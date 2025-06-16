package com.app.libraryproject.dto.event;

public record CreateEventPlanRequest(
        String name,
        String description,
        String proposedBy,
        Long organizerId
) {}