package com.app.libraryproject.controller;

import com.app.libraryproject.dto.event.*;
import com.app.libraryproject.model.EventPlanStatus;
import com.app.libraryproject.service.EventPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/event-plans")
@CrossOrigin(origins = "http://localhost:5173")
public class EventPlanController {
    private final EventPlanService eventPlanService;

    @PostMapping
    public ResponseEntity<Long> createEventPlan(@RequestBody CreateEventPlanRequest request) {
        return ResponseEntity.ok(
                eventPlanService.createPlan(request)
        );
    }

    @PutMapping("/{eventPlanId}/submit")
    public ResponseEntity<Void> submitEventPlan(@PathVariable Long eventPlanId) {
        eventPlanService.submitForApproval(eventPlanId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/modify")
    public ResponseEntity<Void> modifyEventPlan(@RequestBody ModifyEventPlanRequest request) {
        eventPlanService.modifyPlan(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventPlanResponse> getEventPlan(@PathVariable Long id) {
        return ResponseEntity.ok(
                eventPlanService.find(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<EventPlanResponse>> getEventPlans(
            @RequestParam(required = false) Long organizerId,
            @RequestParam(required = false) EventPlanStatus status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer limit
    ) {
        return ResponseEntity.ok(
                eventPlanService.findAll(organizerId, status, page, limit)
        );
    }

    @PutMapping("/{eventPlanId}/approve")
    public ResponseEntity<Void> approvePlan(@PathVariable Long eventPlanId) {
        eventPlanService.approvePlan(eventPlanId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{eventPlanId}/reject")
    public ResponseEntity<Void> rejectPlan(@PathVariable Long eventPlanId) {
        eventPlanService.rejectPlan(eventPlanId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{eventPlanId}/send-back")
    public ResponseEntity<Void> sendBack(@PathVariable Long eventPlanId, @RequestBody String comment) {
        eventPlanService.sendBackWithComment(eventPlanId, comment);
        return ResponseEntity.ok().build();
    }
}
