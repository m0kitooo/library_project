package com.app.libraryproject.controller;

import com.app.libraryproject.dto.proposal.*;
import com.app.libraryproject.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/event")
public class EventController {
    private final EventServiceImpl eventService;

    @PostMapping("proposal/send")
    public ResponseEntity<Long> sendProposal(@RequestBody SendProposalRequest request) {
        return ResponseEntity.ok(
                eventService.addProposal(request)
        );
    }

    @PostMapping("proposal/accept")
    public ResponseEntity<Long> acceptProposal(@RequestParam Long proposalId, @RequestParam Long organizerId) {
        return ResponseEntity.ok(
                eventService.acceptProposal(proposalId, organizerId)
        );
    }

    @PostMapping("proposal/reject")
    public ResponseEntity<Void> rejectProposal(@RequestParam Long proposalId) {
        eventService.rejectProposal(proposalId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("proposal/modify")
    public ResponseEntity<Void> modifyProposal(@RequestBody ModifyProposalRequest request) {
        eventService.modifyProposal(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("proposal/details")
    public ResponseEntity<GetProposalDetailsResponse> getProposalDetails(@RequestParam Long proposalId) {
        return ResponseEntity.ok(
                eventService.getProposalDetails(proposalId)
        );
    }

    @PostMapping("proposal/list")
    public ResponseEntity<GetProposalListResponse> getProposalList(@RequestBody GetProposalListRequest request) {
        return ResponseEntity.ok(
                eventService.getProposalList(request)
        );
    }
}
