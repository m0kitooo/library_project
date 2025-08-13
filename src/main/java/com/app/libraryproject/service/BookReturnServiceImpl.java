package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookreturn.BookReturnResponse;
import com.app.libraryproject.dto.bookreturn.CreateBookReturnRequest;
import com.app.libraryproject.entity.Book;
import com.app.libraryproject.entity.BookLoan;
import com.app.libraryproject.exception.ResourceNotFoundException;
import com.app.libraryproject.model.error.AppError;
import com.app.libraryproject.repository.BookLoanRepository;
import com.app.libraryproject.repository.BookRepository;
import com.app.libraryproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.app.libraryproject.model.error.ErrorCode.BOOK_LOAN_NOT_FOUND;
import static com.app.libraryproject.model.error.ErrorCode.BOOK_NOT_FOUND;
import static com.app.libraryproject.model.error.ErrorCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BookReturnServiceImpl implements BookReturnService {
    private final BookLoanRepository bookLoanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    
    private static final BigDecimal LATE_FEE_PER_DAY = BigDecimal.ONE;
    private static final long LOAN_PERIOD_DAYS = 14;

    @Transactional
    @Override
    public BookReturnResponse returnBook(CreateBookReturnRequest request) {
        memberRepository.findById(request.memberId())
                .orElseThrow(() -> new ResourceNotFoundException(new AppError(MEMBER_NOT_FOUND, "Member not found")));

        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new ResourceNotFoundException(new AppError(BOOK_NOT_FOUND, "Book not found")));

        BookLoan loan = bookLoanRepository
                .findByBookIdAndMemberId(request.bookId(), request.memberId())
                .orElseThrow(() -> new ResourceNotFoundException(new AppError(BOOK_LOAN_NOT_FOUND, "Book loan not found for the given member")));

        book.setQuantity(book.getQuantity() + 1);
        loan.setArchived(true);
        bookRepository.save(book);
        bookLoanRepository.save(loan);

        return new BookReturnResponse(calculateLateFee(loan.getLoanDate().plusDays(LOAN_PERIOD_DAYS)));
    }

    private BigDecimal calculateLateFee(LocalDate dueDate) {
        if (LocalDate.now().isAfter(dueDate)) {
            long daysLate = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
            return LATE_FEE_PER_DAY.multiply(BigDecimal.valueOf(daysLate));
        }
        return BigDecimal.ZERO;
    }
}