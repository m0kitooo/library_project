package com.app.libraryproject.service;

import com.app.libraryproject.dto.event.*;
import com.app.libraryproject.model.EventPlanStatus;
import jakarta.transaction.Transactional;

import java.util.List;

public interface EventPlanService {
    Long createPlan(CreateEventPlanRequest request);
    void submitForApproval(Long eventPlanId);
    void modifyPlan(ModifyEventPlanRequest request);
    EventPlanResponse find(Long eventPlanId);
    List<EventPlanResponse> findAll(
            Long organizerId,
            EventPlanStatus status,
            Integer page,
            Integer limit
    );
    void approvePlan(Long eventPlanId);
    void rejectPlan(Long eventPlanId);
    void sendBackWithComment(Long eventPlanId, String comment);
}
