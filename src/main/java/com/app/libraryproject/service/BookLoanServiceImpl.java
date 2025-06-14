package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookloan.BookLoanResponse;
import com.app.libraryproject.entity.Book;
import com.app.libraryproject.entity.BookLoan;
import com.app.libraryproject.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public BookLoanResponse loanBook(Long bookId, Long memberId) {
        if (bookId == null || memberId == null)
            throw new IllegalArgumentException();

        Book book = bookRepository
                .findByIdAndArchivedFalseAndQuantityGreaterThan(bookId, 0)
                .orElseThrow(NoSuchElementException::new);

        if (libraryCardRepository.findActiveCardByMemberId(memberId).isEmpty())
            throw new NoSuchElementException();

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