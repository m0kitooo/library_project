package com.app.libraryproject.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sponsor")
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "sponsors")
    private List<EventPlan> eventPlans;
}
