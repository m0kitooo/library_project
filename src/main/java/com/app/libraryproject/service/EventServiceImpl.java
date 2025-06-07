package com.app.libraryproject.service;

import com.app.libraryproject.dto.proposal.DecideProposalRequest;
import com.app.libraryproject.dto.proposal.DecideProposalResponse;
import com.app.libraryproject.dto.proposal.SendProposalRequest;
import com.app.libraryproject.dto.proposal.SendProposalResponse;
import com.app.libraryproject.entity.*;
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

    public DecideProposalResponse decideProposal(DecideProposalRequest request) {
        throw new ArithmeticException();
    }
//        Proposal proposal = proposalRepository
//                .findById(id)
//                .orElseThrow();
//        if(proposal.getProposalStatus() == ProposalStatus.REJECTED)
//            //TODO for later change
//            throw new RuntimeException();
//
//        if(request.isAccepted()) {
//            User organizer = userRepository
//                    .findById(request.getOrganizerId())
//                    .orElseThrow();
//
//            String proposedBy = proposalRepository.findById(request.getId())
//                    .orElseThrow()
//                    .getProposedBy();
//
//            proposalRepository.deleteById(request.getId());
//
//            return
//                eventPlanRepository.save(
//                        EventPlan.builder()
//                            .name(request.getTitle())
//                            .description(request.getDescription())
//                            .status(defaultStatus)
//                            .organizer(organizer)
//                            .proposedBy(proposedBy)
//                            .build()
//            ).toDto();
//        } else {
//            proposalRepository.deleteById(request.getId());
//            return ResponseEntity.noContent().build();
//        }
//    }
}
