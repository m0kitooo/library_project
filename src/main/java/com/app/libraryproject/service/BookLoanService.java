package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookloan.BookLoanResponse;

public interface BookLoanService {
    BookLoanResponse loanBook(Long bookId, Long memberId);
}
