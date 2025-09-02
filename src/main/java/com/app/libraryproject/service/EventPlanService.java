package com.app.libraryproject.service;

import com.app.libraryproject.dto.eventplan.*;

public interface EventPlanService {
    Long createEventPlan(CreateEventPlanRequest request);
    void updateEventPlan(UpdateEventPlanRequest request);
    GetEventPlanDetailsResponse getEventPlanDetails(Long eventPlanId);
    GetEventPlanListResponse getEventPlanList(GetEventPlanListRequest request);
    void suspendEventPlan(Long eventPlanId);
    void rejectEventPlan(Long eventPlanId);
    void activateEventPlan(Long eventPlanId);
    void sendForApproval(Long eventPlanId);
    void approveEventPlan(Long eventPlanId);
}
