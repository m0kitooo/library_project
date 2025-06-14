package com.app.libraryproject.dto.bookreservation;

import com.app.libraryproject.dto.book.BookResponse;
import com.app.libraryproject.dto.member.MemberResponse;
import com.app.libraryproject.entity.BookReservation;
import com.app.libraryproject.model.ReservationStatus;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ReservationResponse(
        Long id,
        BookResponse book,
        MemberResponse member,
        LocalDate reservationDate,
        ReservationStatus status,
        LocalDate pickupDeadline
) {
    public static ReservationResponse from(BookReservation reservation) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .book(BookResponse.from(reservation.getBook()))
                .member(MemberResponse.from(reservation.getMember()))
                .reservationDate(reservation.getReservationDate())
                .status(reservation.getStatus())
                .pickupDeadline(reservation.getPickupDeadline())
                .build();
    }
}