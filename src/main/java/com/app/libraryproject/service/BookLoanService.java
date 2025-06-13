package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookloan.BookLoanResponse;

import java.util.List;

public interface BookLoanService {
    List<BookLoanResponse> getAllBookLoan();
    BookLoanResponse loanBook(Long bookId, Long memberId);
    List<BookLoanResponse> findLoansByMemberId(Long memberId);
}