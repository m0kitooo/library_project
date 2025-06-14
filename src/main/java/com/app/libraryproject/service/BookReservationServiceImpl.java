package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookreservation.CreateReservationRequest;
import com.app.libraryproject.dto.bookreservation.ReservationResponse;
import com.app.libraryproject.entity.Book;
import com.app.libraryproject.entity.BookReservation;
import com.app.libraryproject.entity.Member;
import com.app.libraryproject.exception.RecordConflictException;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.model.ReservationStatus;
import com.app.libraryproject.repository.BookLoanRepository;
import com.app.libraryproject.repository.BookRepository;
import com.app.libraryproject.repository.BookReservationRepository;
import com.app.libraryproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookReservationServiceImpl implements BookReservationService {

    private final BookReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BookLoanRepository loanRepository;

    private static final long PICKUP_DAYS = 3;

    @Override
    @Transactional
    public ReservationResponse createReservation(CreateReservationRequest request) {
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new RecordNotFoundException("Book not found with id: " + request.bookId()));

        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new RecordNotFoundException("Member not found with id: " + request.memberId()));

        if (book.getQuantity() > 0) {
            throw new RecordConflictException("Book is available for loan, reservation is not possible.");
        }

        loanRepository.findByBookIdAndMemberId(book.getId(), member.getId()).ifPresent(loan -> {
            throw new RecordConflictException("You have already loaned this book.");
        });

        reservationRepository.findByBookIdAndMemberIdAndStatusIn(book.getId(), member.getId(), List.of(ReservationStatus.ACTIVE, ReservationStatus.WAITING_FOR_PICKUP))
                .ifPresent(reservation -> {
                    throw new RecordConflictException("You already have an active reservation for this book.");
                });


        BookReservation newReservation = BookReservation.builder()
                .book(book)
                .member(member)
                .reservationDate(LocalDate.now())
                .status(ReservationStatus.ACTIVE)
                .build();

        BookReservation savedReservation = reservationRepository.save(newReservation);
        savedReservation.setBook(book);
        savedReservation.setMember(member);

        return ReservationResponse.from(savedReservation);
    }

    @Override
    @Transactional
    public void cancelReservation(Long reservationId, Long memberId) {
        BookReservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RecordNotFoundException("Reservation not found with id: " + reservationId));

        if (!reservation.getMember().getId().equals(memberId)) {
            throw new SecurityException("You are not allowed to cancel this reservation.");
        }

        if(reservation.getStatus() == ReservationStatus.WAITING_FOR_PICKUP){
            processNextReservationForBook(reservation.getBook());
        }

        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationRepository.save(reservation);
    }

    @Override
    public List<ReservationResponse> findReservationsByMember(Long memberId) {
        return reservationRepository.findByMemberId(memberId).stream()
                .map(ReservationResponse::from)
                .toList();
    }

    @Override
    @Transactional
    public void processNextReservationForBook(Book book) {
        Optional<BookReservation> nextReservationOpt = reservationRepository
                .findFirstByBookIdAndStatusOrderByReservationDateAsc(book.getId(), ReservationStatus.ACTIVE);

        if (nextReservationOpt.isPresent()) {
            BookReservation nextReservation = nextReservationOpt.get();
            nextReservation.setStatus(ReservationStatus.WAITING_FOR_PICKUP);
            nextReservation.setPickupDeadline(LocalDate.now().plusDays(PICKUP_DAYS));
            reservationRepository.save(nextReservation);
        } else {
            book.setQuantity(book.getQuantity() + 1);
            bookRepository.save(book);
        }
    }
}