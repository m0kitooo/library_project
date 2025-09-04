package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookloan.BookLoanResponse;
import com.app.libraryproject.dto.bookloan.CreateBookLoanRequest;

import java.util.List;

public interface BookLoanService {
    List<BookLoanResponse> getBookLoans(Boolean archived);
    List<BookLoanResponse> getBookLoansByMember(Long memberId, Boolean archived);
    // List<BookLoanResponse> getAllBookLoan();
    // List<BookLoanResponse> getAllNonArchivedBookLoan();
    BookLoanResponse findByBookId(Long bookId);
    BookLoanResponse loanBook(CreateBookLoanRequest request);
}
