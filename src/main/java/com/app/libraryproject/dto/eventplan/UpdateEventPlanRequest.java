package com.app.libraryproject.dto.eventplan;

import com.app.libraryproject.exception.InvalidRequestArgumentException;
import com.app.libraryproject.model.PlanType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdateEventPlanRequest(
        Long id,
        String name,
        String description,
        BigDecimal estimatedPrice,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Long organizerId,
        PlanType type,
        String sponsors
) {
    public UpdateEventPlanRequest {
        if (id == null) {
            throw new InvalidRequestArgumentException("(id) cannot be null");
        }
        if (name == null || name.isEmpty()) {
            throw new InvalidRequestArgumentException("(name) cannot be null or empty");
        }
    }
}
