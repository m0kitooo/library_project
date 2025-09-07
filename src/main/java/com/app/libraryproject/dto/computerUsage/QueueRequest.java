package com.app.libraryproject.dto.computerUsage;

public record QueueRequest(
        String libraryCardNumber,
        int declaredMinutes
) {
}
