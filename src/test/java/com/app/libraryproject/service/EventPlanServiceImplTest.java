package com.app.libraryproject.service;

import com.app.libraryproject.dto.eventplan.CreateEventPlanRequest;
import com.app.libraryproject.dto.eventplan.GetEventPlanDetailsResponse;
import com.app.libraryproject.entity.EventPlan;
import com.app.libraryproject.entity.User;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.model.PlanStatus;
import com.app.libraryproject.repository.EventPlanRepository;
import com.app.libraryproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class EventPlanServiceImplTest {

    @Mock private EventPlanRepository eventPlanRepository;
    @Mock private UserRepository userRepository;

    @InjectMocks private EventPlanServiceImpl service;

    private User organizer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        organizer = new User();
        organizer.setId(10L);
        organizer.setUsername("JanKowalski");
    }

    @Test
    void createEventPlan_savesWithOrganizer_andReturnsId() {
        CreateEventPlanRequest req = new CreateEventPlanRequest(
                "Nazwa", "Opis", new BigDecimal("100.00"),
                LocalDateTime.now(), LocalDateTime.now().plusHours(2),
                "Adam", 10L
        );

        when(userRepository.findById(10L)).thenReturn(Optional.of(organizer));
        when(eventPlanRepository.save(any(EventPlan.class))).thenAnswer(inv -> {
            EventPlan p = inv.getArgument(0);
            p.setId(1L);
            return p;
        });

        Long id = service.createEventPlan(req);

        assertThat(id).isEqualTo(1L);
        ArgumentCaptor<EventPlan> captor = ArgumentCaptor.forClass(EventPlan.class);
        verify(eventPlanRepository).save(captor.capture());
        EventPlan saved = captor.getValue();
        assertThat(saved.getOrganizer()).isEqualTo(organizer);
        assertThat(saved.getPlanStatus()).isEqualTo(PlanStatus.CREATED);
    }

    @Test
    void createEventPlan_throwsWhenOrganizerMissing() {
        CreateEventPlanRequest req = new CreateEventPlanRequest(
                "Nazwa", "Opis", new BigDecimal("100.00"),
                LocalDateTime.now(), LocalDateTime.now().plusHours(2),
                "Adam", 999L
        );

        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.createEventPlan(req))
                .isInstanceOf(RecordNotFoundException.class);
        verify(eventPlanRepository, never()).save(any());
    }

    @Test
    void getEventPlanDetails_mapsFieldsCorrectly() {
        EventPlan plan = new EventPlan();
        plan.setId(5L);
        plan.setName("Plan");
        plan.setDescription("Opis");
        plan.setEstimatedPrice(new BigDecimal("123.45"));
        plan.setStartTime(LocalDateTime.parse("2025-01-01T10:00:00"));
        plan.setEndTime(LocalDateTime.parse("2025-01-01T12:00:00"));
        plan.setProposedBy("Adam");
        plan.setPlanStatus(PlanStatus.CREATED);
        plan.setOrganizer(organizer);

        when(eventPlanRepository.findById(5L)).thenReturn(Optional.of(plan));

        GetEventPlanDetailsResponse resp = service.getEventPlanDetails(5L);

        assertThat(resp.name()).isEqualTo("Plan");
        assertThat(resp.organizerName()).isEqualTo("Jan Kowalski");
        assertThat(resp.planStatus()).isEqualTo("CREATED");
    }

    @Test
    void statusTransitions_work() {
        EventPlan plan = new EventPlan();
        plan.setId(2L);
        plan.setPlanStatus(PlanStatus.CREATED);
        when(eventPlanRepository.findById(2L)).thenReturn(Optional.of(plan));

        service.sendForApproval(2L);
        assertThat(plan.getPlanStatus()).isEqualTo(PlanStatus.SENT_FOR_APPROVAL);

        service.approveEventPlan(2L);
        assertThat(plan.getPlanStatus()).isEqualTo(PlanStatus.APPROVED);

        when(eventPlanRepository.findById(3L)).thenReturn(Optional.of(new EventPlan()));
        service.suspendEventPlan(3L);
        verify(eventPlanRepository, times(3)).save(any(EventPlan.class));
    }
}
