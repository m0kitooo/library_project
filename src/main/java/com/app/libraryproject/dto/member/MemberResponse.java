package com.app.libraryproject.dto.member;

import com.app.libraryproject.dto.librarycard.LibraryCardResponse;
import java.time.LocalDate;

public record MemberResponse(
        Long id,
        String name,
        String surname,
        String pesel,
        LocalDate birthday,
        LibraryCardResponse latestLibraryCard
) {
    public static MemberResponse from(com.app.libraryproject.entity.Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getSurname(),
                member.getPesel(),
                member.getBirthday(),
                member.getLibraryCards() != null && !member.getLibraryCards().isEmpty()
                        ? LibraryCardResponse.from(
                                member.getLibraryCards()
                                        .stream()
                                        .max((card1, card2) -> card1.getExpiryDate().compareTo(card2.getExpiryDate()))
                                        .orElseThrow())
                        : null
        );
    }
}
