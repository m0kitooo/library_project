package com.app.libraryproject.service;

import com.app.libraryproject.dto.event.EventPlanResponse;
import com.app.libraryproject.dto.proposal.*;
import com.app.libraryproject.entity.EventPlan;
import com.app.libraryproject.entity.Proposal;
import com.app.libraryproject.model.EventPlanStatus;
import com.app.libraryproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventPlanServiceImpl implements EventPlanService {
    private final EventPlanRepository eventPlanRepository;
    private final UserRepository userRepository;

//    @Override
//    public Long addProposal(SendProposalRequest request) {
//        return proposalRepository.save(
//                Proposal
//                        .builder()
//                        .title(request.title())
//                        .description(request.description())
//                        .proposedBy(request.proposedBy())
//                        .status(ProposalStatus.PENDING)
//                        .build()
//        ).getId();
//    }
//
//    @Override
//    @Transactional
//    public Long acceptProposal(Long proposalId, Long organizerId) {
//        Proposal proposal = proposalRepository.findById(proposalId)
//                .orElseThrow(() -> new RecordNotFoundException(
//                        "Proposal with ID " + proposalId + " not found"
//                ));
//
//        if (proposal.getStatus() == ProposalStatus.ACCEPTED) {
//            throw new RecordConflictException(
//                    "Proposal with ID " + proposalId + " has already been accepted"
//            );
//        }
//
//        User organizer = userRepository.findById(organizerId)
//                .orElseThrow(() -> new RecordNotFoundException(
//                        "Organizer with ID " + organizerId + " not found"
//                ));
//
//        proposal.setStatus(ProposalStatus.ACCEPTED);
//        proposalRepository.save(proposal);
//
//        EventPlan eventPlan = EventPlan.builder()
//                .name(proposal.getTitle())
//                .description(proposal.getDescription())
//                .proposedBy(proposal.getProposedBy())
//                .organizer(organizer)
//                .planStatus(PlanStatus.PREPARING)
//                .build();
//
//        return eventPlanRepository.save(eventPlan).getId();
//    }
//
//    @Override
//    public void rejectProposal(Long proposalId) {
//        Proposal proposal = proposalRepository
//                .findById(proposalId)
//                .orElseThrow(() -> new RecordNotFoundException("Proposal not found with id: " + proposalId));
//
//        proposal.setStatus(ProposalStatus.REJECTED);
//        proposalRepository.save(proposal);
//    }
//
//    @Override
//    public void modifyProposal(ModifyProposalRequest request) {
//        Proposal proposal = proposalRepository
//                .findById(request.id())
//                .orElseThrow(() -> new RecordNotFoundException("Proposal not found with id: " + request.id()));
//
//        proposal.setTitle(request.title());
//        proposal.setDescription(request.description());
//        proposalRepository.save(proposal);
//    }
//
//    @Override
//    public GetProposalDetailsResponse getProposalDetails(Long proposalId) {
//        return proposalRepository
//                .findById(proposalId)
//                .orElseThrow(() -> new RecordNotFoundException("Proposal not found with id: " + proposalId))
//                .toDetailsResponse();
//    }
//
    @Override
    public List<EventPlanResponse> findAll(
            Long organizerId,
            EventPlanStatus status,
            Integer page,
            Integer limit
    ) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<EventPlan> eventPlan = eventPlanRepository.findAll(organizerId, status, pageable);

        return eventPlan
                .stream()
                .map(EventPlanResponse::from)
                .toList();
    }
}
