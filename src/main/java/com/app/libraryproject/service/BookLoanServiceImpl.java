package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookloan.BookLoanResponse;
import com.app.libraryproject.entity.Book;
import com.app.libraryproject.entity.BookLoan;
import com.app.libraryproject.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {
    private final BookLoanRepository bookLoanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LibraryCardRepository libraryCardRepository;

    @Transactional
    @Override
    public BookLoanResponse loanBook(Long bookId, Long memberId) {
        if (bookId == null || memberId == null)
            throw new IllegalArgumentException();

        Book book = bookRepository
                .findByIdAndArchivedFalseAndQuantityGreaterThan(bookId, 0)
                .orElseThrow(NoSuchElementException::new);

        if (libraryCardRepository.findActiveCardByMemberId(memberId).isEmpty() ||
                book.getBookReservations().size() >= book.getQuantity())
            throw new NoSuchElementException();

        return bookLoanRepository.save(
                BookLoan
                        .builder()
                        .member(memberRepository
                                .findById(memberId)
                                .orElseThrow(() -> new RuntimeException("Such member doesn't exist"))
                        )
                        .book(book)
                        .build()
        ).toBookLoanResponse();
    }
}
