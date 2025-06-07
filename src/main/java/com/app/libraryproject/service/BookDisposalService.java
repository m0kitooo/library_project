package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookdisposal.BookDisposalResponse;

public interface BookDisposalService {
    BookDisposalResponse loanBook(Long bookId, Long memberId);
}
