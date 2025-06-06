package com.app.libraryproject.service;

import com.app.libraryproject.dto.BookLoanResponse;
import com.app.libraryproject.entity.BookLoan;
import com.app.libraryproject.repository.BookLoanRepository;
import com.app.libraryproject.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {
    private final BookLoanRepository bookLoanRepository;
    private final BookRepository bookRepository;
    private final LibraryCardService libraryCardService;

    @Override
    public BookLoanResponse loanBook(Long bookId, Long memberId) {
        return bookLoanRepository.save(
                BookLoan
                        .builder()
                        .member(null)
                        .book(bookLoanRepository.findById(bookId).orElseThrow().getBook())
                        .build()
        ).toBookLoanResponse();
    }
}
