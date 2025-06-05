package com.app.libraryproject.controller;

import com.app.libraryproject.dto.SendProposalRequest;
import com.app.libraryproject.entity.Proposal;
import com.app.libraryproject.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/event")
public class EventController {
    private final EventService eventService;

    @PostMapping("/sendProposal")
    public String sendProposal(@RequestBody SendProposalRequest request) {
        Proposal proposal = eventService.addProposal(request);

    }
}
