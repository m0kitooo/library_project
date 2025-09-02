package com.app.libraryproject.entity;

import com.app.libraryproject.dto.member.MemberResponse;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Comparator;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "members")
public class Member extends Person {

    @OneToMany(mappedBy = "member")
    private List<LibraryCard> libraryCards;

    @OneToMany(mappedBy = "member")
    private List<BookLoan> bookLoans;

    @OneToMany(mappedBy = "member")
    private List<BookReservation> bookReservations;

    @Column(name = "pesel", nullable = false, unique = true, length = 11)
    private String pesel;

    @Column(nullable = false)
    private String address;

    public MemberResponse toMemberResponse() {
        LibraryCard latestCard = null;
        if (libraryCards != null && !libraryCards.isEmpty()) {
            latestCard = libraryCards.stream()
                    .filter(c -> c.getId() != null)
                    .max(Comparator.comparing(LibraryCard::getId))
                    .orElse(null);
        }

        return new MemberResponse(
                id,
                name,
                surname,
                pesel,
                birthday,
                address,
                latestCard != null ? latestCard.toLibraryCardResponse() : null
        );
    }
}