package com.app.libraryproject.service;

import com.app.libraryproject.dto.*;
import com.app.libraryproject.entity.*;
import com.app.libraryproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final ProposalRepository proposalRepository;
    private final EventPlanRepository eventPlanRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;

    public SendProposalResponse addProposal(SendProposalRequest request) {
        return proposalRepository.save(
                        Proposal.builder()
                            .title(request.title())
                            .description(request.description())
                            .build()
        ).toDto();
    }

    public ResponseEntity<DecideProposalResponse> decideProposal(DecideProposalRequest request) {
        if(request.isAccepted()) {
            User organizer = userRepository.findById(request.getOrganizerId())
                    .orElseThrow();

            Status defaultStatus = statusRepository.findByName("NEW")
                    .orElseGet(() -> statusRepository.save(Status.builder().name("NEW").build()));

            String proposedBy = proposalRepository.findById(request.getId())
                    .orElseThrow()
                    .getProposedBy();

            proposalRepository.deleteById(request.getId());

            return ResponseEntity.ok(
                eventPlanRepository.save(
                        EventPlan.builder()
                            .name(request.getTitle())
                            .description(request.getDescription())
                            .status(defaultStatus)
                            .organizer(organizer)
                            .proposedBy(proposedBy)
                            .build()
            ).toDto());
        } else {
            proposalRepository.deleteById(request.getId());
            return ResponseEntity.noContent().build();
        }
    }
}
