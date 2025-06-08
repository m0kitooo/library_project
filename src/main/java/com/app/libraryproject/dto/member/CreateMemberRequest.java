package com.app.libraryproject.dto.member;

import com.app.libraryproject.entity.Member;
import com.app.libraryproject.exception.InvalidRequestArgumentException;

import java.time.LocalDate;

import static org.apache.logging.log4j.util.Strings.isBlank;

public record CreateMemberRequest(
        String name,
        String surname,
        String nationalId,
        LocalDate birthday
) {
    private final static String nationalIdRegex = "^[0-9]{11}$";

    public CreateMemberRequest {
        if (isBlank(name))
            throw new InvalidRequestArgumentException("Name can't be null or blank");
        if (isBlank(surname))
            throw new InvalidRequestArgumentException("Surname can't be null or blank");
        if (nationalId == null || !nationalId.matches(nationalIdRegex))
            throw new InvalidRequestArgumentException(
                    "NationalId can't be null and has to match regex: " + nationalIdRegex);
        if (birthday == null)
            throw new InvalidRequestArgumentException("Birthday can't be null");
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
