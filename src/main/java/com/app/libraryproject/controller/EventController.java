package com.app.libraryproject.controller;

import com.app.libraryproject.dto.proposal.ModifyProposalRequest;
import com.app.libraryproject.dto.proposal.SendProposalRequest;
import com.app.libraryproject.dto.proposal.SendProposalResponse;
import com.app.libraryproject.service.EventServiceImpl;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/proposal/accept")
    public ResponseEntity<Long> acceptProposal(@RequestParam Long proposalId, @RequestParam Long organizerId) {
        return ResponseEntity.ok(
                eventService.acceptProposal(proposalId, organizerId)
                        .getId()
        );
    }

    @PostMapping("/proposal/reject")
    public ResponseEntity<Void> rejectProposal(@RequestParam Long proposalId) {
        eventService.rejectProposal(proposalId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/proposal/modify")
    public ResponseEntity<Void> modifyProposal(@RequestBody ModifyProposalRequest request) {
        eventService.modifyProposal(request);
        return ResponseEntity.noContent().build();
    }
}
