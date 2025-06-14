package com.app.libraryproject.dto.event;

import com.app.libraryproject.entity.EventPlan;
import com.app.libraryproject.model.EventPlanStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record EventPlanResponse(
        Long id,
        String name,
        String description,
        BigDecimal estimatedPrice,
        LocalDateTime startTime,
        LocalDateTime endTime,
        List<Sponsor> sponsors,
        EventPlanStatus status,
        Organizer organizer,
        String proposedBy
        ) {

    public static EventPlanResponse from(final EventPlan eventPlan) {
        return EventPlanResponse.builder()
                .id(eventPlan.getId())
                .name(eventPlan.getName())
                .description(eventPlan.getDescription())
                .estimatedPrice(eventPlan.getEstimatedPrice())
                .startTime(eventPlan.getStartTime())
                .endTime(eventPlan.getEndTime())
                .sponsors(
                        eventPlan.getSponsors() == null ? List.of() : eventPlan.getSponsors().stream()
                                .map(EventPlanResponse.Sponsor::from)
                                .toList() // tymczasowe
                )
                .status(eventPlan.getStatus())
                .organizer(eventPlan.getOrganizer() != null ? EventPlanResponse.Organizer.from(eventPlan.getOrganizer()) : null)
                .proposedBy(eventPlan.getProposedBy())
                .build();
    }

    public record Sponsor(
            String name,
            BigDecimal amountTransferred
    ) {
        public static Sponsor from(com.app.libraryproject.entity.Sponsor sponsor) {
            return new Sponsor(
                    sponsor.getName(),
                    sponsor.getAmountTransferred() != null ? sponsor.getAmountTransferred() : BigDecimal.ZERO
            );
        }
    }

    public record Organizer (
            Long id,
            String name,
            String surname
    ) {
        public static Organizer from(com.app.libraryproject.entity.User user) {
            return new Organizer(
                    user.getId(),
                    user.getName(),
                    user.getSurname()
            );
        }
    }
}
