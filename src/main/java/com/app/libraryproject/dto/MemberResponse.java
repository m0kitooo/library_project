package com.app.libraryproject.dto;

import java.time.LocalDate;

public record MemberResponse(
        Long id,
        String name,
        String surname,
        String nationalId,
        LocalDate birthday
) { }
