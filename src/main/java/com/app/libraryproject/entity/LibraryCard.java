package com.app.libraryproject.entity;

import com.app.libraryproject.dto.librarycard.GetLibraryCardDetailsResponse;
import com.app.libraryproject.dto.librarycard.LibraryCardResponse;
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id", nullable = false)
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

    public GetLibraryCardDetailsResponse toLibraryCardDetails() {
        return GetLibraryCardDetailsResponse.builder()
                .cardId(id)
                .memberId(member.getId())
                .expiryDate(expiryDate)
                .build();
    }

    public LibraryCardResponse toLibraryCardResponse() {
        return LibraryCardResponse
                .builder()
                .id(id)
                .member(member.toMemberResponse())
                .expiryDate(expiryDate)
                .build();
    }
}
