package com.app.libraryproject.service;

import com.app.libraryproject.dto.BookLoanResponse;
import com.app.libraryproject.entity.BookLoan;
import com.app.libraryproject.repository.BookLoanRepository;
import com.app.libraryproject.repository.BookRepository;
import com.app.libraryproject.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {
    private final BookLoanRepository bookLoanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LibraryCardService libraryCardService;

    @Override
    public BookLoanResponse loanBook(Long bookId, Long memberId) {
        return bookLoanRepository.save(
                BookLoan
                        .builder()
                        .member(memberRepository
                                .findById(memberId)
                                .orElseThrow(() -> new RuntimeException("Such member doesn't exist"))
                        )
                        .book(bookRepository
                                .findByIdAndArchivedFalseAndQuantityGreaterThan(bookId, 0)
                                .orElseThrow(() -> new RuntimeException("Such book doesn't exist"))
                        ).build()
        ).toBookLoanResponse();
    }
}
