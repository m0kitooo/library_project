package com.app.libraryproject.service;

import com.app.libraryproject.dto.proposal.ModifyProposalRequest;
import com.app.libraryproject.dto.proposal.SendProposalRequest;
import com.app.libraryproject.dto.proposal.SendProposalResponse;
import com.app.libraryproject.entity.*;
import com.app.libraryproject.model.ProposalStatus;
import com.app.libraryproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final ProposalRepository proposalRepository;
    private final EventPlanRepository eventPlanRepository;
    private final UserRepository userRepository;

    @Override
    public SendProposalResponse addProposal(SendProposalRequest request) {
        return proposalRepository.save(
                Proposal
                        .builder()
                        .title(request.title())
                        .description(request.description())
                        .build()
        ).toDto();
    }

    @Override
    public EventPlan acceptProposal(Long proposalId, Long organizerId) {
        Proposal proposal = proposalRepository
                .findById(proposalId)
                .orElseThrow();
        proposal.setStatus(ProposalStatus.ACCEPTED);
        proposalRepository.save(proposal);

        if(proposal.getStatus() == ProposalStatus.REJECTED)
            throw new RuntimeException();

        User organizer = userRepository
                .findById(organizerId)
                .orElseThrow();

        EventPlan eventPlan = proposal.toEventPlan(organizer);

        return eventPlanRepository.save(eventPlan);
    }

    @Override
    public void rejectProposal(Long proposalId) {
        Proposal proposal = proposalRepository
                .findById(proposalId)
                .orElseThrow();

        if(proposal.getStatus() == ProposalStatus.REJECTED)
            throw new RuntimeException();

        proposal.setStatus(ProposalStatus.REJECTED);
        proposalRepository.save(proposal);
    }

    @Override
    public void modifyProposal(ModifyProposalRequest request) {
        Proposal proposal = proposalRepository
                .findById(request.getId())
                .orElseThrow();

        if(proposal.getStatus() == ProposalStatus.REJECTED)
            throw new RuntimeException();

        proposal.setTitle(request.getTitle());
        proposal.setDescription(request.getDescription());
        proposalRepository.save(proposal);
    }
}
