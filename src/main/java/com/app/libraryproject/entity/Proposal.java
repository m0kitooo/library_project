package com.app.libraryproject.entity;

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

    public SendProposalResponse toDto() {
        return SendProposalResponse.builder()
                .id(id)
                .title(title)
                .description(description)
                .build();
    }
}
