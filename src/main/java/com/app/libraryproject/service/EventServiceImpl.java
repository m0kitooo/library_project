package com.app.libraryproject.service;

import com.app.libraryproject.dto.SendProposalRequest;
import com.app.libraryproject.dto.SendProposalResponse;
import com.app.libraryproject.entity.Proposal;
import com.app.libraryproject.repository.ProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final ProposalRepository proposalRepository;

    public ResponseEntity<SendProposalResponse> addProposal(SendProposalRequest request) {
        return ResponseEntity.ok(
                proposalRepository.save(
                        Proposal.builder()
                            .title(request.title())
                            .description(request.description())
                            .build()
        ).toDto());
    }
}
