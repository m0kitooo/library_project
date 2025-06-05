package com.app.libraryproject.service;

import com.app.libraryproject.dto.SendProposalRequest;
import com.app.libraryproject.entity.Proposal;
import com.app.libraryproject.repository.ProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService implements EventServiceInterface {
    private final ProposalRepository proposalRepository;

    public Proposal addProposal(SendProposalRequest request) {
        Proposal proposal = new Proposal();
        proposal.setTitle(request.title());
        proposal.setDescription(request.description());

        return proposalRepository.save(proposal);
    }
}
