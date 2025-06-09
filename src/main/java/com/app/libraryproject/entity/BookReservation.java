package com.app.libraryproject.entity;

import com.app.libraryproject.dto.bookreservation.BookReservationResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "book_reservations")
public class BookReservation {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "reservation_time", nullable = false)
    private LocalDateTime reservationTime;

    public BookReservationResponse toBookReservationResponse() {
        return new BookReservationResponse(id, book.toBookResponse(), member.toMemberResponse(), reservationTime);
    }
}
