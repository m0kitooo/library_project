package com.app.libraryproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "sponsors")
public class Sponsor {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "sponsors")
    private List<EventPlan> eventPlans;

    @Column(name = "amount_transferred")
    private BigDecimal amountTransferred;
}
