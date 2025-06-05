package com.app.libraryproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "library_card_expiry_update_history")
public class LibraryCardExpiryUpdateHistory {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "library_card_id", nullable = false)
    private LibraryCard libraryCard;

    @Column(name = "old_expiry_date", nullable = false)
    private LocalDate oldExpiryDate;

    @Column(name = "new_expiry_date", nullable = false)
    private LocalDate newExpiryDate;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;
}
