package com.app.libraryproject.entity;

import com.app.libraryproject.dto.SendProposalResponse;
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

    @Column(name = "proposal_status")
    @Enumerated(EnumType.STRING)
    private ProposalStatus proposalStatus;

    @Column(nullable = false)
    private String description;

    @Column(name = "proposed_by")
    private String proposedBy;

    public SendProposalResponse toDto() {
        return SendProposalResponse.builder()
                .id(id)
                .title(title)
                .description(description)
                .proposedBy(proposedBy)
                .build();
    }
}
