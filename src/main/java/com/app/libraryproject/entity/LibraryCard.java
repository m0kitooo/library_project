package com.app.libraryproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "library_cards")
public class LibraryCard {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @Column(name = "created_at", nullable = false)
    private LocalDate creationDate;

    @Column(nullable = false)
    private boolean active;
}
