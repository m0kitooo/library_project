package com.app.libraryproject.repository;

import com.app.libraryproject.entity.BookReservation;
import com.app.libraryproject.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {

    Optional<BookReservation> findByBookIdAndMemberIdAndStatusIn(Long bookId, Long memberId, List<ReservationStatus> statuses);

    List<BookReservation> findByMemberId(Long memberId);

    Optional<BookReservation> findFirstByBookIdAndStatusOrderByReservationDateAsc(Long bookId, ReservationStatus status);

    Optional<BookReservation> findByBookIdAndMemberIdAndStatus(Long bookId, Long memberId, ReservationStatus status);

    List<BookReservation> findAllByStatusAndPickupDeadlineBefore(ReservationStatus status, LocalDate date);
}