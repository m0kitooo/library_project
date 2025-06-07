package com.app.libraryproject.repository;

import com.app.libraryproject.entity.BookReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {
}
