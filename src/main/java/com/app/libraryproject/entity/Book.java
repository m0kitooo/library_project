package com.app.libraryproject.entity;

import com.app.libraryproject.dto.book.BookResponse;
import com.app.libraryproject.model.BookStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;
import jakarta.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "accession_number_id", referencedColumnName = "id", unique = true)
    private AccessionNumberSequence accessionNumberSequence;

    @Column(name = "ISBN")
    @Pattern(regexp = "^(\\d{10}|\\d{13})$", message = "ISBN has to be 10 or 13 digits length")
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    // sygnatura
    @Column(name = "call_number", nullable = false)
    private String callNumber;

    private String publisher;

    // wydanie
    @Column(nullable = false)
    private String edition;

    @Column(name = "publication_year", nullable = false)
    @Check(constraints = "publication_year >= 0")
    private Integer publicationYear;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean archived;

    @OneToOne(mappedBy = "book")
    private BookLoan bookLoan;

    @OneToOne(mappedBy = "book")
    private BookReservation bookReservation;

    public BookResponse toBookResponse() {
        Long bookLoanId = getBookLoan() != null
                ? getBookLoan().getId()
                : null;

        Long bookReservationId = getBookReservation() != null
                ? getBookReservation().getId()
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
                .id(id)
                .accessionNumber(getAccessionNumberSequence().getId())
                .isbn(isbn)
                .title(title)
                .author(author)
                .callNumber(callNumber)
                .publisher(publisher)
                .edition(edition)
                .publicationYear(publicationYear)
                .status(status)
                .bookLoanId(bookLoanId)
                .bookReservationId(bookReservationId)
                .build();
    }
}
