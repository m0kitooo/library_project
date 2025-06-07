package com.app.libraryproject.dto.member;

import com.app.libraryproject.entity.Member;

import java.time.LocalDate;

public record CreateMemberRequest(
        String name,
        String surname,
        String nationalId,
        LocalDate birthday
) {
    public Member toMember() {
        return Member
                .builder()
                .name(name)
                .surname(surname)
                .nationalId(nationalId)
                .birthday(birthday)
                .build();
    }
}
