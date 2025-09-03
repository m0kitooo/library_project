package com.app.libraryproject.entity;

import com.app.libraryproject.dto.proposal.GetProposalDetailsResponse;
import com.app.libraryproject.dto.proposal.GetProposalListResponse;
import com.app.libraryproject.model.PlanStatus;
import com.app.libraryproject.model.PlanType;
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
    @Column(name = "proposal_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "proposal_status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
        private ProposalStatus status;

    @Column(length = 2000)
    private String comments;

    @Column(name = "proposed_by")
    private String proposedBy;

    @Enumerated(EnumType.STRING)
    private PlanType type;

    public GetProposalDetailsResponse toDetailsResponse() {
        return GetProposalDetailsResponse.builder()
                .id(id)
                .title(title)
                .description(description)
                .status(status.name())
                .proposedBy(proposedBy)
                .type(type)
                .build();
    }


    public GetProposalListResponse.ProposalListItem toListItem() {
        return GetProposalListResponse.ProposalListItem.builder()
                .id(id)
                .title(title)
                .description(description)
                .status(status.name())
                .proposedBy(proposedBy)
                .build();
    }

}
