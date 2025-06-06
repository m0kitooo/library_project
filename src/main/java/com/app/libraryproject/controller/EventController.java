package com.app.libraryproject.controller;

import com.app.libraryproject.dto.*;
import com.app.libraryproject.entity.Proposal;
import com.app.libraryproject.service.EventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/event")
public class EventController {
    private final EventServiceImpl eventService;

    @PostMapping("/proposal/send")
    public ResponseEntity<SendProposalResponse> sendProposal(@RequestBody SendProposalRequest request) {
        return new ResponseEntity<>(eventService.addProposal(request), HttpStatus.CREATED);
    }

    @PostMapping("/proposal/decide")
    public ResponseEntity<DecideProposalResponse> acceptProposal(@RequestBody DecideProposalRequest request) {
//        return eventService.decideProposal(request);
        return null;
    }
}
