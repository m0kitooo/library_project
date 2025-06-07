package com.app.libraryproject.dto.bookreservation;

import com.app.libraryproject.dto.book.BookResponse;
import com.app.libraryproject.dto.member.MemberResponse;

import java.time.LocalDateTime;

public record BookReservationResponse(
        Long id,
        BookResponse bookResponse,
        MemberResponse memberResponse,
        LocalDateTime reservationDate
) { }
