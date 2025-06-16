package com.app.libraryproject.service;

import com.app.libraryproject.dto.event.*;
import com.app.libraryproject.entity.*;
import com.app.libraryproject.exception.*;
import com.app.libraryproject.model.EventPlanStatus;
import com.app.libraryproject.repository.*;
import com.app.libraryproject.validate.EventPlanValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventPlanServiceImpl implements EventPlanService {
    private final EventPlanRepository eventPlanRepository;
    private final UserRepository userRepository;
    private final SponsorRepository sponsorRepository;

    @Override
    public Long createPlan(CreateEventPlanRequest request) {
        User organizer = userRepository.findById(request.organizerId())
                .orElseThrow(() -> new RecordNotFoundException("Organizer not found with id: " + request.organizerId()));

        EventPlan eventPlan = EventPlan.builder()
                .name(request.name())
                .description(request.description())
                .organizer(organizer)
                .proposedBy(request.proposedBy())
                .status(EventPlanStatus.PREPARING)
                .build();

        return eventPlanRepository.save(eventPlan).getId();
    }

    @Transactional
    @Override
    public void submitForApproval(Long eventPlanId) {
        EventPlan eventPlan = eventPlanRepository.findById(eventPlanId)
                .orElseThrow(() -> new RecordNotFoundException("Event plan not found with id: " + eventPlanId));

        if (eventPlan.getStatus() == EventPlanStatus.SENT) {
            throw new RecordConflictException("Event plan already submitted for approval");
        }

        List<String> errors = EventPlanValidator.validateForFinalApproval(eventPlan);
        if (!errors.isEmpty()) { throw new InvalidRequestArgumentException(errors.toString()); }


        eventPlan.setStatus(EventPlanStatus.SENT);
        eventPlanRepository.save(eventPlan);
    }

    @Override
    public void modifyPlan(ModifyEventPlanRequest request) {
        EventPlan eventPlan = eventPlanRepository.findById(request.id())
                .orElseThrow(() -> new RecordNotFoundException("Event plan not found with id: " + request.id()));

        eventPlan.setName(request.name());
        eventPlan.setDescription(request.description());
        eventPlan.setStartTime(request.startTime());
        eventPlan.setEndTime(request.endTime());
        eventPlan.setEstimatedPrice(request.estimatedPrice());

        eventPlanRepository.save(eventPlan);
    }

    @Override
    public EventPlanResponse find(Long eventPlanId) {
        EventPlan plan = eventPlanRepository.findById(eventPlanId)
                .orElseThrow(() -> new RecordNotFoundException("Event plan not found with id: " + eventPlanId));

        return EventPlanResponse.from(plan);
    }

    @Override
    public List<EventPlanResponse> findAll(Long organizerId, EventPlanStatus status, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<EventPlan> plans = eventPlanRepository.findAll(organizerId, status, pageable);

        return plans.stream()
                .map(EventPlanResponse::from)
                .toList();
    }

    @Transactional
    @Override
    public void approvePlan(Long eventPlanId) {
        EventPlan plan = eventPlanRepository.findById(eventPlanId)
                .orElseThrow(() -> new RecordNotFoundException("Event plan not found"));

        if (plan.getStatus() != EventPlanStatus.SENT) {
            throw new IllegalStateException("Only plans in SENT status can be approved");
        }

        plan.setStatus(EventPlanStatus.FINAL_APPROVED);
        eventPlanRepository.save(plan);
    }

    @Transactional
    @Override
    public void rejectPlan(Long eventPlanId) {
        EventPlan plan = eventPlanRepository.findById(eventPlanId)
                .orElseThrow(() -> new RecordNotFoundException("Event plan not found"));

        if (plan.getStatus() != EventPlanStatus.SENT) {
            throw new IllegalStateException("Only plans in SENT status can be rejected");
        }

        plan.setStatus(EventPlanStatus.REJECTED);
        eventPlanRepository.save(plan);
    }

    @Transactional
    @Override
    public void sendBackWithComment(Long eventPlanId, String comment) {
        EventPlan plan = eventPlanRepository.findById(eventPlanId)
                .orElseThrow(() -> new RecordNotFoundException("Event plan not found"));

        if (plan.getStatus() != EventPlanStatus.SENT) {
            throw new IllegalStateException("Only plans in SENT status can be returned");
        }

        plan.setStatus(EventPlanStatus.PREPARING);
        // TODO: save comment somewhere if needed
        eventPlanRepository.save(plan);
    }
}
