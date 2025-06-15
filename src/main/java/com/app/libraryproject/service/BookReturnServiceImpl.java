package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookreturn.BookReturnResponse;
import com.app.libraryproject.dto.bookreturn.CreateBookReturnRequest;
import com.app.libraryproject.entity.Book;
import com.app.libraryproject.entity.BookLoan;
import com.app.libraryproject.entity.MemberPayment;
import com.app.libraryproject.exception.ResourceNotFoundException;
import com.app.libraryproject.model.MemberPaymentType;
import com.app.libraryproject.repository.BookLoanRepository;
import com.app.libraryproject.repository.BookRepository;
import com.app.libraryproject.repository.MemberPaymentRepository;
import com.app.libraryproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class BookReturnServiceImpl implements BookReturnService {
    private final BookLoanRepository bookLoanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final MemberPaymentRepository memberPaymentRepository;
    private final BookReservationService bookReservationService;
    private final DisposedBookService disposedBookService; // WSTRZYKNIĘTY SERWIS

    private static final double LATE_FEE_PER_DAY = 0.50;
    private static final double DAMAGE_FEE_AMOUNT = 25.0;
    private static final long LOAN_PERIOD_DAYS = 14;

    @Transactional
    @Override
    public BookReturnResponse returnBook(CreateBookReturnRequest request) {
        var member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + request.memberId()));

        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + request.bookId()));

        BookLoan loan = bookLoanRepository
                .findByBookIdAndMemberId(request.bookId(), request.memberId())
                .orElseThrow(() -> new ResourceNotFoundException("Book loan not found for this member and book."));

        LocalDate dueDate = loan.getLoanDate().plusDays(LOAN_PERIOD_DAYS);
        boolean isLate = LocalDate.now().isAfter(dueDate);
        double lateFee = 0.0;

        if (isLate) {
            long daysLate = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
            lateFee = daysLate * LATE_FEE_PER_DAY;

            MemberPayment latePayment = MemberPayment.builder()
                    .memberPaymentType(MemberPaymentType.LATE_RETURN_FEE)
                    .amount(BigDecimal.valueOf(lateFee))
                    .paymentDate(LocalDate.now())
                    .build();
            memberPaymentRepository.save(latePayment);
        }

        double damageFee = 0.0;
        if (request.isDamaged()) {
            damageFee = DAMAGE_FEE_AMOUNT;

            MemberPayment damagePayment = MemberPayment.builder()
                    .memberPaymentType(MemberPaymentType.DAMAGE_FEE)
                    .amount(BigDecimal.valueOf(damageFee))
                    .paymentDate(LocalDate.now())
                    .build();
            memberPaymentRepository.save(damagePayment);

            // Zamiast zwiększać ilość, dodajemy do utylizacji
            disposedBookService.addDisposedBook(book);

        } else {
            // Sprawdzamy rezerwacje, jeśli książka nie jest uszkodzona
            bookReservationService.processNextReservationForBook(book);
        }

        bookLoanRepository.delete(loan);

        String message = buildResponseMessage(isLate, lateFee, request.isDamaged(), damageFee);

        return new BookReturnResponse(loan.getId(), message, isLate, lateFee, request.isDamaged(), damageFee);
    }

    private String buildResponseMessage(boolean isLate, double lateFee, boolean isDamaged, double damageFee) {
        StringBuilder sb = new StringBuilder("Książka została zwrócona. ");
        if (isLate) {
            sb.append("Naliczono opłatę za spóźnienie: ").append(String.format("%.2f", lateFee)).append(" zł. ");
        }
        if (isDamaged) {
            sb.append("Zarejestrowano uszkodzenie książki i przeznaczono ją do utylizacji. Naliczono opłatę: ").append(String.format("%.2f", damageFee)).append(" zł.");
        }
        if (!isLate && !isDamaged) {
            sb.append("Zwrot przebiegł pomyślnie.");
        }
        return sb.toString().trim();
    }
}