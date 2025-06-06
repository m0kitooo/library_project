package com.app.libraryproject.controller;

import com.app.libraryproject.dto.SendProposalRequest;
import com.app.libraryproject.dto.SendProposalResponse;
import com.app.libraryproject.entity.Proposal;
import com.app.libraryproject.service.EventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/event")
public class EventController {
    private final EventServiceImpl eventService;

    @PostMapping("/proposal/send")
    public ResponseEntity<SendProposalResponse> sendProposal(@RequestBody SendProposalRequest request) {
        return eventService.addProposal(request);
    }

    //@PostMapping("/proposal/decide")
    //public ResponseEntity<AcceptProposalResponse> acceptProposal(@RequestBody DecideProposalRequest request) {}
}
