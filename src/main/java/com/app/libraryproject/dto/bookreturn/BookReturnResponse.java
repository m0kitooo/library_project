package com.app.libraryproject.dto.bookreturn;

public record BookReturnResponse(
        Long id,
        String message,
        boolean isLate,
        double lateFee,
        boolean isDamaged,
        double damageFee
) {}