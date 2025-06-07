package com.app.libraryproject.service;

import com.app.libraryproject.dto.bookreservation.BookReservationResponse;

public interface BookReservationService {
    BookReservationResponse loanBook(Long bookId, Long memberId);
}
