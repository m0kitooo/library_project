package com.app.libraryproject.dto.bookreservation;

import com.app.libraryproject.dto.book.BookResponse;
import com.app.libraryproject.dto.member.MemberResponse;
import com.app.libraryproject.entity.BookReservation;

import java.time.LocalDateTime;

public record BookReservationResponse(
        Long id,
        BookResponse bookResponse,
        MemberResponse memberResponse,
        LocalDateTime reservationTime
) {
    public static BookReservationResponse from(BookReservation bookReservation) {
        return new BookReservationResponse(
                bookReservation.getId(),
                BookResponse.from(bookReservation.getBook()),
                MemberResponse.from(bookReservation.getMember()),
                bookReservation.getReservationTime()
        );
    }
}
