package com.app.libraryproject.service;

import com.app.libraryproject.dto.CreateBookRequest;
import com.app.libraryproject.entity.BookLoan;
import com.app.libraryproject.repository.BookLoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {
    private final BookLoanRepository bookLoanRepository;
    private final BookLoanService bookLoanService;
    private final LibraryCardService libraryCardService;

    public void loanBook(Long id) {
//        libraryCardService
        bookLoanRepository.save(
                BookLoan
                        .builder()
                        .build()
        );
    }
}
