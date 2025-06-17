// src/main/java/com/app/libraryproject/dto/bookreturn/BookReturnResponse.java
package com.app.libraryproject.dto.bookreturn;

public record BookReturnResponse(
        Long id,
        String message,
        boolean isLate,
        double lateFee,
        boolean isDamaged, // Nowe pole
        double damageFee  // Nowe pole
) {}