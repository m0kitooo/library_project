package com.app.libraryproject.dto;

import java.time.LocalDate;

public record CreateLibraryCardRequest(
        String name,
        String surname,
        LocalDate birthday
) { }
