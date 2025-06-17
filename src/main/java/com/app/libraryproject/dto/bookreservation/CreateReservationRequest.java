package com.app.libraryproject.dto.bookreservation;

public record CreateReservationRequest(
        Long bookId,
        Long memberId
) {}