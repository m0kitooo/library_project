package com.app.libraryproject.dto.member;

import com.app.libraryproject.entity.Member;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MemberResponse(
        Long id,
        String name,
        String surname,
        String nationalId,
        LocalDate birthday
) {
    public static MemberResponse from(Member member) {
        return MemberResponse
                .builder()
                .id(member.getId())
                .name(member.getName())
                .surname(member.getSurname())
                .nationalId(member.getNationalId())
                .birthday(member.getBirthday())
                .build();
    }
}
