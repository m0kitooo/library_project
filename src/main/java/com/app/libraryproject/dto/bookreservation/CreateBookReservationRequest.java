package com.app.libraryproject.dto.bookreservation;

import com.app.libraryproject.dto.book.CreateBookRequest;

import java.time.LocalDate;

public record CreateBookReservationRequest(
        CreateBookRequest book,
        LocalDate reservationDate
) { }
