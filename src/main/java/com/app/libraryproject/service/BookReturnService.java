package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookreturn.BookReturnResponse;

public interface BookReturnService {
    BookReturnResponse loanBook(Long bookId, Long memberId);
}
