package com.app.libraryproject.dto.bookreturn;

public record BookReturnResponse(
    Long id,
    boolean isLate,
    double lateFee
) {}