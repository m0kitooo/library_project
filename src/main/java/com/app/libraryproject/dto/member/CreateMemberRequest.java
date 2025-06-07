package com.app.libraryproject.dto.member;

import com.app.libraryproject.entity.Member;

import java.time.LocalDate;

public record CreateMemberRequest(
        String name,
        String surname,
        String nationalId,
        LocalDate birthday
) {
    public CreateMemberRequest {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or blank");
        }
        if (surname == null || surname.isBlank()) {
            throw new IllegalArgumentException("surname cannot be null or blank");
        }
        if (nationalId == null || nationalId.isBlank()) {
            throw new IllegalArgumentException("nationalId cannot be null or blank");
        }
        if (birthday == null) {
            throw new IllegalArgumentException("birthday cannot be null");
        }
    }

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
