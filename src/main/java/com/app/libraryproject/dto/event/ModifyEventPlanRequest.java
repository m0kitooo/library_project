package com.app.libraryproject.dto.event;

import com.app.libraryproject.entity.EventPlan;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ModifyEventPlanRequest(
        Long id,
        String name,
        String description,
        LocalDateTime startTime,
        LocalDateTime endTime,
        BigDecimal estimatedPrice,
        List<Long> sponsorIds
) {}