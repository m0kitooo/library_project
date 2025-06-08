package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookreturn.BookReturnResponse;
import com.app.libraryproject.dto.bookreturn.CreateBookReturnRequest;
import com.app.libraryproject.entity.Book;
import com.app.libraryproject.entity.BookLoan;
import com.app.libraryproject.entity.Member;
import com.app.libraryproject.exception.ResourceNotFoundException;
import com.app.libraryproject.repository.BookLoanRepository;
import com.app.libraryproject.repository.BookRepository;
import com.app.libraryproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class BookReturnServiceImpl {
    private final BookLoanRepository bookLoanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    
    private static final double LATE_FEE_PER_DAY = 1.0;
    private static final long LOAN_PERIOD_DAYS = 14;

    @Transactional
    public BookReturnResponse returnBook(CreateBookReturnRequest request) {
        Member member = memberRepository.findById(request.memberId())
            .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono czytelnika"));

        Book book = bookRepository.findById(request.bookId())
            .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono książki"));

        BookLoan loan = bookLoanRepository.findByBookIdAndMemberIdAndReturnDateIsNull(
                request.bookId(), request.memberId())
            .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono wypożyczenia"));

        LocalDate dueDate = loan.getLoanDate().plusDays(LOAN_PERIOD_DAYS);
        boolean isLate = LocalDate.now().isAfter(dueDate);
        double lateFee = 0.0;

        if (isLate) {
            long daysLate = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
            lateFee = daysLate * LATE_FEE_PER_DAY;
        }

        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
        bookLoanRepository.delete(loan);

        String message = isLate 
            ? "Książka zwrócona po terminie. Naliczono opłatę: " + lateFee + " zł"
            : "Książka zwrócona pomyślnie";

        return new BookReturnResponse(loan.getId(), message, isLate, lateFee);
    }
}