package com.app.libraryproject.service;

import com.app.libraryproject.entity.BookReservation;
import com.app.libraryproject.model.ReservationStatus;
import com.app.libraryproject.repository.BookReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReservationScheduler {

    private final BookReservationRepository reservationRepository;
    private final BookReservationService bookReservationService;

    /**
     * Uruchamia się codziennie o 1:00 w nocy.
     * Znajduje i przetwarza przeterminowane rezerwacje.
     */
    @Scheduled(cron = "0 0 1 * * *")
    @Transactional
    public void handleExpiredReservations() {
        log.info("Starting scheduled task: Handling expired reservations...");

        List<BookReservation> expiredReservations = reservationRepository
                .findAllByStatusAndPickupDeadlineBefore(ReservationStatus.WAITING_FOR_PICKUP, LocalDate.now());

        if (expiredReservations.isEmpty()) {
            log.info("No expired reservations found. Task finished.");
            return;
        }

        log.info("Found {} expired reservations to process.", expiredReservations.size());

        for (BookReservation reservation : expiredReservations) {
            log.info("Processing expired reservation with ID: {}. Book ID: {}, Member ID: {}",
                    reservation.getId(), reservation.getBook().getId(), reservation.getMember().getId());

            reservation.setStatus(ReservationStatus.EXPIRED);
            reservationRepository.save(reservation);

            bookReservationService.processNextReservationForBook(reservation.getBook());
        }

        log.info("Scheduled task: Handling expired reservations finished successfully.");
    }
}