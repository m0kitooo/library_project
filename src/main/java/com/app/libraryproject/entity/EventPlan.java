package com.app.libraryproject.entity;

import com.app.libraryproject.dto.DecideProposalRequest;
import com.app.libraryproject.dto.DecideProposalResponse;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "event_plans")
public class EventPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_plan_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "estimated_price")
    private BigDecimal estimatedPrice;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToMany
    @JoinTable(
            name = "event_plan_sponsor",
            joinColumns = @JoinColumn(name = "event_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "sponsor_id")
    )
    private List<Sponsor> sponsors;

    @OneToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User organizer;

    @Column(name = "proposed_by")
    private String proposedBy;

    public DecideProposalResponse toDto() {
        return DecideProposalResponse.builder()
                .id(id)
                .build();
    }
}
