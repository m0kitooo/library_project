package com.app.libraryproject.dto.member;

import com.app.libraryproject.dto.librarycard.LibraryCardResponse;
import com.app.libraryproject.entity.LibraryCard;
import com.app.libraryproject.entity.Member;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MemberResponse(
        Long id,
        String name,
        String surname,
        String nationalId,
        LocalDate birthday,
        LibraryCardResponse libraryCard
) {
    public static MemberResponse from(Member member) {
        return MemberResponse
                .builder()
                .id(member.getId())
                .name(member.getName())
                .surname(member.getSurname())
                .nationalId(member.getNationalId())
                .birthday(member.getBirthday())
                .libraryCard(member.getLibraryCard() != null
                        ? LibraryCardResponse.from(member.getLibraryCard())
                        : null)
                .build();
    }



}
