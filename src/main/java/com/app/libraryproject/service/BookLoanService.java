package com.app.libraryproject.service;

import com.app.libraryproject.dto.BookLoanResponse;

public interface BookLoanService {
    BookLoanResponse loanBook(Long bookId, Long memberId);
}
