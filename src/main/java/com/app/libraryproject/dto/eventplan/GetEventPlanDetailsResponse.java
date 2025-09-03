package com.app.libraryproject.dto.eventplan;

import com.app.libraryproject.model.PlanType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Builder
public record GetEventPlanDetailsResponse(
        Long id,
        String name,
        String description,
        BigDecimal estimatedPrice,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime startTime,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime endTime,
        String proposedBy,
        String planStatus,
        String organizerName,
        PlanType type,
        String sponsors
) {}
