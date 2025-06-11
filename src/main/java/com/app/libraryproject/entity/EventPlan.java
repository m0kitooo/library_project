package com.app.libraryproject.entity;

import com.app.libraryproject.model.PlanStatus;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
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

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ManyToMany
    @JoinTable(
            name = "event_plan_sponsor",
            joinColumns = @JoinColumn(name = "event_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "sponsor_id")
    )
    private List<Sponsor> sponsors;

    @Column(name = "event_status")
    @Enumerated(EnumType.STRING)
    private PlanStatus planStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", unique = false)
    private User organizer;

    @Column(name = "proposed_by")
    private String proposedBy;
}
