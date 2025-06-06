package com.app.libraryproject.entity;

import com.app.libraryproject.dto.LibraryCardResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "library_cards")
public class LibraryCard {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    private LocalDate creationDate;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @Column(name = "deactivated_at")
    private LocalDate deactivationDate;

    @Column(name = "deactivation_reason")
    private String deactivationReason;

    public LibraryCardResponse toDto() {
        return LibraryCardResponse.builder()
                .id(id)
                .expiryDate(expiryDate)
                .build();
    }
}
