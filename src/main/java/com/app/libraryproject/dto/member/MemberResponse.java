package com.app.libraryproject.dto.member;

import com.app.libraryproject.entity.Member;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MemberResponse(
        Long id,
        String name,
        String surname,
        String pesel,
        LocalDate birthday,
        String address
) {
    public Member toMember() {
        return Member
                .builder()
                .id(id)
                .name(name)
                .surname(surname)
                .pesel(pesel)
                .birthday(birthday)
                .address(address)
                .build();
    }

    public static MemberResponse from(Member member) {
        return MemberResponse
                .builder()
                .id(member.getId())
                .name(member.getName())
                .surname(member.getSurname())
                .pesel(member.getPesel())
                .birthday(member.getBirthday())
                .address(member.getAddress())
                .build();
    }
}
