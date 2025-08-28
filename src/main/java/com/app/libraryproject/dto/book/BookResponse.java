package com.app.libraryproject.dto.book;

import com.app.libraryproject.dto.bookloan.BookLoanResponse;
import com.app.libraryproject.dto.bookreservation.BookReservationResponse;
import com.app.libraryproject.entity.Book;
import com.app.libraryproject.model.BookStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record BookResponse(
        Long id,
        String isbn,
        String title,
        String author,
        String publisher,
        String edition,
        Integer publicationYear,
        BookStatus status,
        @JsonProperty("bookLoan") BookLoanResponse bookLoanResponse,
        @JsonProperty("bookReservation")BookReservationResponse bookReservationResponse
        ) {
    public static BookResponse from(Book book) {
        BookLoanResponse loanResponse = book.getBookLoan() != null
                ? BookLoanResponse.from(book.getBookLoan())
                : null;

        BookReservationResponse reservationResponse = book.getBookReservation() != null
                ? BookReservationResponse.from(book.getBookReservation())
                : null;

        BookStatus status;
        if (loanResponse != null) {
            status = BookStatus.LOANED;
        } else if (reservationResponse != null) {
            status = BookStatus.RESERVED;
        } else {
            status = BookStatus.AVAILABLE;
        }

        return BookResponse
                .builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .edition(book.getEdition())
                .publicationYear(book.getPublicationYear())
                .status(status)
                .bookLoanResponse(loanResponse)
                .bookReservationResponse(reservationResponse).build();
    }
}
