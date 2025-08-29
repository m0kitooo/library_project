package com.app.libraryproject.dto.book;

import com.app.libraryproject.entity.Book;
import com.app.libraryproject.model.BookStatus;
import lombok.Builder;

@Builder
public record BookResponse(
        Long id,
        Long accessionNumber,
        String isbn,
        String title,
        String author,
        String callNumber,
        String publisher,
        String edition,
        Integer publicationYear,
        BookStatus status,
        Long bookLoanId,
        Long bookReservationId
        ) {
    public static BookResponse from(Book book) {
        Long bookLoanId = book.getBookLoan() != null
                ? book.getBookLoan().getId()
                : null;

        Long bookReservationId = book.getBookReservation() != null
                ? book.getBookReservation().getId()
                : null;

        BookStatus status;
        if (bookLoanId != null) {
            status = BookStatus.LOANED;
        } else if (bookReservationId != null) {
            status = BookStatus.RESERVED;
        } else {
            status = BookStatus.AVAILABLE;
        }

        return BookResponse
                .builder()
                .id(book.getId())
                .accessionNumber(book.getAccessionNumberSequence().getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .callNumber(book.getCallNumber())
                .publisher(book.getPublisher())
                .edition(book.getEdition())
                .publicationYear(book.getPublicationYear())
                .status(status)
                .bookLoanId(bookLoanId)
                .bookReservationId(bookReservationId)
                .build();
    }
}
