package com.app.libraryproject.dto.eventplan;

import com.app.libraryproject.entity.EventPlan;
import com.app.libraryproject.model.PlanStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class EventPlanDto {
    Long id;
    String name;
    String description;
    LocalDateTime startTime;
    LocalDateTime endTime;
    PlanStatus planStatus;
    String proposedBy;

    Long organizerId;
    String organizerUsername;

    public static EventPlanDto fromEntity(EventPlan e) {
        if (e == null) return null;

        return EventPlanDto.builder()
                .id(e.getId())
                .name(e.getName())
                .description(e.getDescription())
                .startTime(e.getStartTime())
                .endTime(e.getEndTime())
                .planStatus(e.getPlanStatus())
                .proposedBy(e.getProposedBy())
                .organizerId(e.getOrganizer() != null ? e.getOrganizer().getId() : null)
                .organizerUsername(e.getOrganizer() != null ? e.getOrganizer().getUsername() : null)
                .build();
    }

    public static EventPlanDto map(EventPlan e) {
        return fromEntity(e);
    }
}
