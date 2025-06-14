package com.app.libraryproject.controller;

import com.app.libraryproject.dto.proposal.*;
import com.app.libraryproject.model.ProposalStatus;
import com.app.libraryproject.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/proposals")
@CrossOrigin(origins = "http://localhost:5173")
public class ProposalController {
    private final ProposalServiceImpl eventService;

    @PostMapping
    public ResponseEntity<Long> sendProposal(@RequestBody SendProposalRequest request) {
        return ResponseEntity.ok(
                eventService.addProposal(request)
        );
    }

    @PutMapping("{proposalId}/accept")
    public ResponseEntity<Long> acceptProposal(
            @PathVariable Long proposalId,
            @RequestParam Long organizerId
    ) {
        return ResponseEntity.ok(
                eventService.acceptProposal(proposalId, organizerId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> rejectProposal(@PathVariable Long id) {
        eventService.rejectProposal(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/modify")
    public ResponseEntity<Void> modifyProposal(
            @RequestBody ModifyProposalRequest request
    ) {
        eventService.modifyProposal(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProposalResponse> getProposals(@PathVariable Long id) {
        return ResponseEntity.ok(
                eventService.find(id)
        );
    }

    @GetMapping
    public ResponseEntity<GetProposalListResponse> getProposalList(
            @RequestParam(required = false) ProposalStatus status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer limit
            ) {
        return ResponseEntity.ok(
                eventService.getProposals(status, page, limit)
        );
    }
}
