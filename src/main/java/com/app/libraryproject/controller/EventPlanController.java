package com.app.libraryproject.controller;

import com.app.libraryproject.dto.event.EventPlanResponse;
import com.app.libraryproject.model.EventPlanStatus;
import com.app.libraryproject.service.EventPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/event-plans")
@CrossOrigin(origins = "http://localhost:5173")
public class EventPlanController {
    private final EventPlanService eventPlanService;

    @GetMapping
    public List<EventPlanResponse> getEventPlans(
            @RequestParam(required = false) Long organizerId,
            @RequestParam(required = false) EventPlanStatus status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer limit
    ) { return eventPlanService.findAll(organizerId, status, page, limit); }
}
