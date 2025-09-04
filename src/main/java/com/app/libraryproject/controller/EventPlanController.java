package com.app.libraryproject.controller;

import com.app.libraryproject.dto.eventplan.*;
import com.app.libraryproject.service.EventPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/event-plan")
public class EventPlanController {

    private final EventPlanService eventPlanService;

    @PostMapping("/create")
    public ResponseEntity<Long> createEventPlan(@RequestBody CreateEventPlanRequest request) {
        return ResponseEntity.ok(eventPlanService.createEventPlan(request));
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateEventPlan(@RequestBody UpdateEventPlanRequest request) {
        eventPlanService.updateEventPlan(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/details/{planId}")
    public ResponseEntity<GetEventPlanDetailsResponse> getEventPlanDetails(@PathVariable Long planId) {
        return ResponseEntity.ok(eventPlanService.getEventPlanDetails(planId));
    }

    @PostMapping("/list")
    public ResponseEntity<GetEventPlanListResponse> getEventPlanList(@RequestBody GetEventPlanListRequest request) {
        return ResponseEntity.ok(eventPlanService.getEventPlanList(request));
    }

    @PostMapping("/suspend/{eventPlanId}")
    public ResponseEntity<Void> suspendEventPlan(@PathVariable Long eventPlanId) {
        eventPlanService.suspendEventPlan(eventPlanId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reject/{eventPlanId}")
    public ResponseEntity<Void> rejectEventPlan(@PathVariable Long eventPlanId) {
        eventPlanService.rejectEventPlan(eventPlanId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/activate/{eventPlanId}")
    public ResponseEntity<Void> activateEventPlan(@PathVariable Long eventPlanId) {
        eventPlanService.activateEventPlan(eventPlanId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send-for-approval/{eventPlanId}")
    public ResponseEntity<Void> sendForApproval(@PathVariable Long eventPlanId) {
        eventPlanService.sendForApproval(eventPlanId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/approve/{eventPlanId}")
    public ResponseEntity<Void> approveEventPlan(@PathVariable Long eventPlanId) {
        eventPlanService.approveEventPlan(eventPlanId);
        return ResponseEntity.ok().build();
    }
}
