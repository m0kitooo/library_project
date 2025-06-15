package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookloan.BookLoanResponse;
import com.app.libraryproject.dto.bookloan.CreateBookLoanRequest;

import java.util.List;

public interface BookLoanService {
    List<BookLoanResponse> getAllBookLoan();
    BookLoanResponse findByBookId(Long bookId);
    BookLoanResponse loanBook(CreateBookLoanRequest request);
}
