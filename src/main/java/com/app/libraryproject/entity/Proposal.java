package com.app.libraryproject.entity;

import com.app.libraryproject.dto.DecideProposalResponse;
import com.app.libraryproject.dto.SendProposalResponse;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "proposals")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

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
