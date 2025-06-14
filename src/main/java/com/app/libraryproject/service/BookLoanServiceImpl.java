package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookloan.BookLoanResponse;
import com.app.libraryproject.entity.Book;
import com.app.libraryproject.entity.BookLoan;
import com.app.libraryproject.entity.BookReservation;
import com.app.libraryproject.model.ReservationStatus;
import com.app.libraryproject.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {
    private final BookLoanRepository bookLoanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LibraryCardRepository libraryCardRepository;
    private final BookReservationRepository reservationRepository; // WSTRZYKNIĘTE REPOZYTORIUM

    @Override
    public List<BookLoanResponse> getAllBookLoan() {
        return bookLoanRepository
                .findAll()
                .stream()
                .map(BookLoanResponse::from)
                .toList();
    }

    @Transactional
    @Override
    public BookLoanResponse loanBook(Long bookId, Long memberId) {
        if (bookId == null || memberId == null) {
            throw new IllegalArgumentException("Book ID and Member ID cannot be null.");
        }

        if (libraryCardRepository.findActiveCardByMemberId(memberId).isEmpty()) {
            throw new NoSuchElementException("Member does not have an active library card.");
        }

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + bookId));

        Optional<BookReservation> waitingReservationOpt = reservationRepository
                .findByBookIdAndMemberIdAndStatus(bookId, memberId, ReservationStatus.WAITING_FOR_PICKUP);

        if (waitingReservationOpt.isPresent()) {
            BookReservation reservation = waitingReservationOpt.get();
            reservation.setStatus(ReservationStatus.FULFILLED);
            reservationRepository.save(reservation);
        } else {
            if (book.getQuantity() <= 0) {
                throw new NoSuchElementException("Book is not available for loan.");
            }
            book.setQuantity(book.getQuantity() - 1);
            bookRepository.save(book);
        }

        return bookLoanRepository.save(
                BookLoan
                        .builder()
                        .member(memberRepository
                                .findById(memberId)
                                .orElseThrow(() -> new RuntimeException("Such member doesn't exist"))
                        )
                        .book(book)
                        .loanDate(java.time.LocalDate.now())
                        .build()
        ).toBookLoanResponse();
    }

    @Override
    public List<BookLoanResponse> findLoansByMemberId(Long memberId) {
        if (memberId == null) {
            throw new IllegalArgumentException("Member ID cannot be null.");
        }
        return bookLoanRepository.findAllByMemberId(memberId)
                .stream()
                .map(BookLoanResponse::from)
                .toList();
    }
}