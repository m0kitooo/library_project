package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookloan.BookLoanResponse;
import com.app.libraryproject.entity.BookLoan;

public interface BookLoanService {
    BookLoanResponse loanBook(Long bookId, Long memberId);
    BookLoan loanBookOnSite(Long bookId, Long memberId);
}
