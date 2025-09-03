package com.app.libraryproject.service;

import com.app.libraryproject.dto.proposal.*;
import com.app.libraryproject.entity.*;
import com.app.libraryproject.exception.RecordConflictException;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.model.PlanStatus;
import com.app.libraryproject.model.ProposalStatus;
import com.app.libraryproject.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.*;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProposalServiceImpl implements ProposalService {
    private final ProposalRepository proposalRepository;
    private final EventPlanRepository eventPlanRepository;
    private final UserRepository userRepository;

    @Override
    public Long addProposal(SendProposalRequest request) {
        return proposalRepository.save(
                Proposal
                        .builder()
                        .title(request.title())
                        .description(request.description())
                        .proposedBy(request.proposedBy())
                        .status(ProposalStatus.PENDING)
                        .build()
        ).getId();
    }

    @Override
    @Transactional
    public Long acceptProposal(Long proposalId, Long organizerId) {
        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RecordNotFoundException(
                        "Proposal with ID " + proposalId + " not found"
                ));

        if (proposal.getStatus() == ProposalStatus.ACCEPTED) {
            throw new RecordConflictException(
                    "Proposal with ID " + proposalId + " has already been accepted"
            );
        }

        User organizer = userRepository.findById(organizerId)
                .orElseThrow(() -> new RecordNotFoundException(
                        "Organizer with ID " + organizerId + " not found"
                ));

        proposal.setStatus(ProposalStatus.ACCEPTED);
        proposalRepository.save(proposal);

        EventPlan eventPlan = EventPlan.builder()
                .name(proposal.getTitle())
                .description(proposal.getDescription())
                .proposedBy(proposal.getProposedBy())
                .organizer(organizer)
                .planStatus(PlanStatus.PREPARING)
                .build();

        return eventPlanRepository.save(eventPlan).getId();
    }

    @Override
    public void rejectProposal(Long proposalId) {
        Proposal proposal = proposalRepository
                .findById(proposalId)
                .orElseThrow(() -> new RecordNotFoundException("Proposal not found with id: " + proposalId));

        proposal.setStatus(ProposalStatus.REJECTED);
        proposalRepository.save(proposal);
    }

    @Override
    public void modifyProposal(ModifyProposalRequest request) {
        Proposal proposal = proposalRepository
                .findById(request.id())
                .orElseThrow(() -> new RecordNotFoundException("Proposal not found with id: " + request.id()));

        proposal.setTitle(request.title());
        proposal.setDescription(request.description());
        proposalRepository.save(proposal);
    }

    @Override
    public GetProposalDetailsResponse getProposalDetails(Long proposalId) {
        return proposalRepository
                .findById(proposalId)
                .orElseThrow(() -> new RecordNotFoundException("Proposal not found with id: " + proposalId))
                .toDetailsResponse();
    }

    @Override
    public GetProposalListResponse getProposalList(GetProposalListRequest request) {
        Pageable pageable = PageRequest.of(request.page(), request.limit());
        Page<Proposal> proposals = proposalRepository.findAll(request.status(), pageable);

        return new GetProposalListResponse(
                proposals.stream()
                        .map(Proposal::toListItem)
                        .toList()
        );
    }

    private Proposal getProposalOrThrow(Long id) {
        Optional<Proposal> proposalOpt = proposalRepository.findById(id);
        if (proposalOpt.isEmpty()) {
            throw new IllegalArgumentException("Proposal with id " + id + " not found");
        }
        return proposalOpt.get();
    }
}
