package com.app.libraryproject.controller;

import com.app.libraryproject.dto.proposal.*;
import com.app.libraryproject.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/proposal")
public class ProposalController {
    private final ProposalServiceImpl eventService;

    @PostMapping("/send")
    public ResponseEntity<Long> sendProposal(@RequestBody SendProposalRequest request) {
        return ResponseEntity.ok(eventService.addProposal(request));
    }

    @PostMapping("/accept")
    public ResponseEntity<Map<String, Object>> acceptProposal(@RequestParam Long proposalId, @RequestParam Long organizerId) {
        Long eventPlanId = eventService.acceptProposal(proposalId, organizerId);
        return ResponseEntity.ok(Map.of("eventPlanId", eventPlanId));
    }

    @PostMapping("/reject")
    public ResponseEntity<Void> rejectProposal(@RequestParam Long proposalId) {
        eventService.rejectProposal(proposalId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/modify")
    public ResponseEntity<Void> modifyProposal(@RequestBody ModifyProposalRequest request) {
        eventService.modifyProposal(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/details")
    public ResponseEntity<GetProposalDetailsResponse> getProposalDetails(@RequestParam Long proposalId) {
        return ResponseEntity.ok(
                eventService.getProposalDetails(proposalId)
        );
    }

    @PostMapping("/list")
    public ResponseEntity<GetProposalListResponse> getProposalList(@RequestBody GetProposalListRequest request) {
        return ResponseEntity.ok(
                eventService.getProposalList(request)
        );
    }
}
