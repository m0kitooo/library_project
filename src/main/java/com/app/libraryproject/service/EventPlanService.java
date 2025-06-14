package com.app.libraryproject.service;

import com.app.libraryproject.dto.event.EventPlanResponse;
import com.app.libraryproject.model.EventPlanStatus;

import java.util.List;

public interface EventPlanService {
    List<EventPlanResponse> findAll(
            Long organizerId,
            EventPlanStatus status,
            Integer page,
            Integer limit
    );
}
