package com.app.libraryproject.dto.eventplan;

import com.app.libraryproject.exception.InvalidRequestArgumentException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateEventPlanRequest(
        String name,
        String description,
        BigDecimal estimatedPrice,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String proposedBy,
        Long organizerId
) {
    public CreateEventPlanRequest {
        if (name == null || name.isEmpty()) {
            throw new InvalidRequestArgumentException("(name) cannot be null or empty");
        }
    }
}
