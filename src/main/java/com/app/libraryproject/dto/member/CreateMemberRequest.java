package com.app.libraryproject.dto.member;

import com.app.libraryproject.entity.Member;
import com.app.libraryproject.exception.InvalidRequestArgumentException;

import java.time.LocalDate;

import static org.apache.commons.lang3.StringUtils.isBlank;


public record CreateMemberRequest(
        String name,
        String surname,
        String pesel,
        LocalDate birthday,
        String address
) {
    private final static String peselRegex = "^[0-9]{11}$";

    public CreateMemberRequest {
        if (isBlank(name))
            throw new InvalidRequestArgumentException("Name can't be null or blank");
        if (isBlank(surname))
            throw new InvalidRequestArgumentException("Surname can't be null or blank");
        if (pesel == null || !pesel.matches(peselRegex))
            throw new InvalidRequestArgumentException(
                    "Pesel can't be null and has to match regex: " + peselRegex);
        if (birthday == null)
            throw new InvalidRequestArgumentException("Birthday can't be null");
        if (birthday.isAfter(LocalDate.now().minusYears(13)))
            throw new InvalidRequestArgumentException("Member must be at least 13 years old");
        if (isBlank(address))
            throw new InvalidRequestArgumentException("Address can't be null or blank");
    }

    public Member toMember() {
        return Member
                .builder()
                .name(name)
                .surname(surname)
                .pesel(pesel)
                .birthday(birthday)
                .address(address)
                .build();
    }
}
