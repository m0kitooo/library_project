package com.app.libraryproject.service;

import com.app.libraryproject.dto.eventplan.*;
import com.app.libraryproject.entity.EventPlan;
import com.app.libraryproject.entity.Notification;
import com.app.libraryproject.entity.User;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.model.PlanStatus;
import com.app.libraryproject.model.WorkerRole;
import com.app.libraryproject.repository.EventPlanRepository;
import com.app.libraryproject.repository.NotificationRepository;
import com.app.libraryproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventPlanServiceImpl implements EventPlanService {

    private final EventPlanRepository eventPlanRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    @Override
    @Transactional
    public Long createEventPlan(CreateEventPlanRequest request) {
        User organizer = userRepository.findById(request.organizerId())
                .orElseThrow(() -> new RecordNotFoundException("Organizer not found"));

        EventPlan eventPlan = EventPlan.builder()
                .name(request.name())
                .description(request.description())
                .estimatedPrice(request.estimatedPrice())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .proposedBy(request.proposedBy())
                .planStatus(PlanStatus.CREATED)
                .organizer(organizer)
                .build();

        eventPlanRepository.save(eventPlan);

        // 🔔 Notyfikacja dla MANAGER
        notificationRepository.save(Notification.builder()
                .content("Utworzono nowy plan wydarzenia: " + eventPlan.getName())
                .role(WorkerRole.MANAGER)
                .build());

        return eventPlan.getId();
    }

    @Override
    public GetEventPlanListResponse getEventPlanList(GetEventPlanListRequest request) {
        int page = request.page() != null ? request.page() : 0;
        int size = request.limit() != null ? request.limit() : 10;

        Pageable pageable = PageRequest.of(page, size);
        Page<EventPlan> result = (request.status() == null)
                ? eventPlanRepository.findAll(pageable)
                : eventPlanRepository.findAllByPlanStatus(request.status(), pageable);

        return GetEventPlanListResponse.fromPage(result);
    }

    @Override
    @Transactional
    public void updateEventPlan(UpdateEventPlanRequest request) {
        EventPlan plan = eventPlanRepository.findById(request.id())
                .orElseThrow(() -> new RecordNotFoundException("Plan not found"));
        plan.setName(request.name());
        plan.setDescription(request.description());
        plan.setEstimatedPrice(request.estimatedPrice());
        plan.setStartTime(request.startTime());
        plan.setEndTime(request.endTime());
        eventPlanRepository.save(plan);

        notificationRepository.save(Notification.builder()
                .content("Plan \"" + plan.getName() + "\" został zaktualizowany.")
                .role(WorkerRole.MANAGER)
                .user(plan.getOrganizer())
                .build());
    }

    @Override
    @Transactional(readOnly = true)
    public GetEventPlanDetailsResponse getEventPlanDetails(Long planId) {
        EventPlan plan = eventPlanRepository.findById(planId)
                .orElseThrow(() -> new RecordNotFoundException("Plan not found"));
        return new GetEventPlanDetailsResponse(
                plan.getId(),
                plan.getName(),
                plan.getDescription(),
                plan.getEstimatedPrice(),
                plan.getStartTime(),
                plan.getEndTime(),
                plan.getProposedBy(),
                plan.getPlanStatus().name(),
                plan.getOrganizer().getUsername()
        );
    }

    @Override
    @Transactional
    public void suspendEventPlan(Long eventPlanId) {
        EventPlan plan = eventPlanRepository.findById(eventPlanId)
                .orElseThrow(() -> new RecordNotFoundException("Plan not found"));
        plan.setPlanStatus(PlanStatus.SUSPENDED);
        eventPlanRepository.save(plan);

        notificationRepository.save(Notification.builder()
                .content("Plan \"" + plan.getName() + "\" został zawieszony.")
                .role(WorkerRole.MANAGER)
                .user(plan.getOrganizer())
                .build());
    }

    @Override
    @Transactional
    public void rejectEventPlan(Long eventPlanId) {
        EventPlan plan = eventPlanRepository.findById(eventPlanId)
                .orElseThrow(() -> new RecordNotFoundException("Plan not found"));
        plan.setPlanStatus(PlanStatus.REJECTED);
        eventPlanRepository.save(plan);

        notificationRepository.save(Notification.builder()
                .content("Plan \"" + plan.getName() + "\" został odrzucony.")
                .role(WorkerRole.MANAGER)
                .user(plan.getOrganizer())
                .build());
    }

    @Override
    @Transactional
    public void activateEventPlan(Long eventPlanId) {
        EventPlan plan = eventPlanRepository.findById(eventPlanId)
                .orElseThrow(() -> new RecordNotFoundException("Plan not found"));
        plan.setPlanStatus(PlanStatus.ACTIVE);
        eventPlanRepository.save(plan);

        notificationRepository.save(Notification.builder()
                .content("Plan \"" + plan.getName() + "\" został aktywowany.")
                .role(WorkerRole.MANAGER)
                .user(plan.getOrganizer())
                .build());
    }

    @Override
    @Transactional
    public void sendForApproval(Long eventPlanId) {
        EventPlan plan = eventPlanRepository.findById(eventPlanId)
                .orElseThrow(() -> new RecordNotFoundException("Plan not found"));
        plan.setPlanStatus(PlanStatus.SENT_FOR_APPROVAL);
        eventPlanRepository.save(plan);

        notificationRepository.save(Notification.builder()
                .content("Plan \"" + plan.getName() + "\" został wysłany do akceptacji.")
                .role(WorkerRole.MANAGER)
                .user(plan.getOrganizer())
                .build());
    }

    @Override
    @Transactional
    public void approveEventPlan(Long eventPlanId) {
        EventPlan plan = eventPlanRepository.findById(eventPlanId)
                .orElseThrow(() -> new RecordNotFoundException("Plan not found"));
        plan.setPlanStatus(PlanStatus.APPROVED);
        eventPlanRepository.save(plan);

        notificationRepository.save(Notification.builder()
                .content("Plan \"" + plan.getName() + "\" został zatwierdzony.")
                .role(WorkerRole.MANAGER)
                .user(plan.getOrganizer())
                .build());
    }
}
