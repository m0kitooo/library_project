package com.app.libraryproject.controller;

import com.app.libraryproject.dto.proposal.*;
import com.app.libraryproject.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class EventController {
    private final EventServiceImpl eventService;

    @PostMapping("/proposal/send")
    public ResponseEntity<Long> sendProposal(@RequestBody SendProposalRequest request) {
        return ResponseEntity.ok(
                eventService.addProposal(request)
        );
    }

    @PostMapping("/proposal/accept")
    public ResponseEntity<Map<String, Object>> acceptProposal(@RequestParam Long proposalId, @RequestParam Long organizerId) {
        Long eventPlanId = eventService.acceptProposal(proposalId, organizerId);
        return ResponseEntity.ok(Map.of("eventPlanId", eventPlanId));
    }

    @PostMapping("/proposal/reject")
    public ResponseEntity<Void> rejectProposal(@RequestParam Long proposalId) {
        eventService.rejectProposal(proposalId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/proposal/modify")
    public ResponseEntity<Void> modifyProposal(@RequestBody ModifyProposalRequest request) {
        eventService.modifyProposal(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/proposal/details")
    public ResponseEntity<GetProposalDetailsResponse> getProposalDetails(@RequestParam Long proposalId) {
        return ResponseEntity.ok(
                eventService.getProposalDetails(proposalId)
        );
    }

    @GetMapping("proposal/list")
    public ResponseEntity<GetProposalListResponse> getProposalList(@RequestBody GetProposalListRequest request) {
        return ResponseEntity.ok(
                eventService.getProposalList(request)
        );
    }
}
