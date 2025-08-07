package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookloan.BookLoanResponse;
import com.app.libraryproject.dto.bookloan.CreateBookLoanRequest;
import com.app.libraryproject.entity.Book;
import com.app.libraryproject.entity.BookLoan;
import com.app.libraryproject.exception.RecordNotFoundException;
import com.app.libraryproject.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {
    private final BookLoanRepository bookLoanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LibraryCardRepository libraryCardRepository;

    @Override
    public List<BookLoanResponse> getAllBookLoan() {
        return bookLoanRepository
                .findAll()
                .stream()
                .map(BookLoanResponse::from)
                .toList();
    }

    @Override
    public BookLoanResponse findByBookId(Long bookId) {
        return BookLoanResponse.from(bookLoanRepository
                .findByBookId(bookId)
                .orElseThrow(() -> new RecordNotFoundException("Book loan not found"))
        );
    }

    @Transactional
    @Override
    public BookLoanResponse loanBook(CreateBookLoanRequest request) {
        Book book = bookRepository
                .findByIdAndArchivedFalseAndQuantityGreaterThan(request.bookId(), 0)
                
                .orElseThrow(() -> new RecordNotFoundException("Book not found"));

        if (libraryCardRepository.findActiveCardByMemberId(request.memberId()).isEmpty() ||
                book.getBookReservations().size() >= book.getQuantity())
            throw new NoSuchElementException();

        return bookLoanRepository.save(
                BookLoan
                        .builder()
                        .member(memberRepository
                                .findById(request.memberId())
                                .orElseThrow(() -> new RecordNotFoundException("Such member doesn't exist"))
                        )
                        .book(book)
                        .loanDate(LocalDate.now())
                        .build()
        ).toBookLoanResponse();
    }
}
