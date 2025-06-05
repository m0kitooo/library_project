package com.app.libraryproject.dto;

import java.time.LocalDate;

public record CreateLibraryCardResponse(
        Long id,
        String name,
        String surname,
        LocalDate creationDate,
        LocalDate expireDate,
        boolean active
) {}