package com.app.libraryproject.dto;

import java.time.LocalDate;

public record CreateMemberRequest(
        String name,
        String surname,
        LocalDate birthday
) { }
