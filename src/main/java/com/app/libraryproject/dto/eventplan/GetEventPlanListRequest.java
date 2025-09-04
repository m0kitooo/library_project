package com.app.libraryproject.dto.eventplan;

import com.app.libraryproject.exception.InvalidRequestArgumentException;
import com.app.libraryproject.model.PlanStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetEventPlanListRequest(
        @JsonProperty("planStatus") PlanStatus status,
        Integer page,
        Integer limit
) {
    public GetEventPlanListRequest {
        if (page == null || limit == null) {
            throw new InvalidRequestArgumentException("(page, limit) cannot be null");
        }
    }
}
