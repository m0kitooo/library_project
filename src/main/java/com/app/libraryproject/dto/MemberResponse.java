package com.app.libraryproject.dto;

import java.time.LocalDate;

public record MemberResponse(
        String name,
        String surname,
        String nationalId,
        LocalDate birthday
) { }
