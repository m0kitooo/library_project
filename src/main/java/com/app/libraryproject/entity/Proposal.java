package com.app.libraryproject.entity;

import com.app.libraryproject.dto.proposal.GetProposalDetailsResponse;
import com.app.libraryproject.model.PlanStatus;
import com.app.libraryproject.model.ProposalStatus;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "proposals")
public class Proposal {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "proposal_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProposalStatus status;

    @Column(name = "proposed_by")
    private String proposedBy;

    public EventPlan toEventPlan(User organizer) {
        return EventPlan.builder()
                .name(title)
                .description(description)
                .proposedBy(proposedBy)
                .organizer(organizer)
                .planStatus(PlanStatus.PREPARING)
                .build();
    }

    public GetProposalDetailsResponse toDetailsResponse() {
        return GetProposalDetailsResponse.builder()
                .title(title)
                .description(description)
                .status(status.name())
                .proposedBy(proposedBy)
                .build();
    }
}
