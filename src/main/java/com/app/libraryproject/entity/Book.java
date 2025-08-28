package com.app.libraryproject.entity;

import com.app.libraryproject.dto.book.BookResponse;
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

    @Column(name = "ISBN")
    @Pattern(regexp = "^(\\d{10}|\\d{13})$", message = "ISBN has to be 10 or 13 digits length")
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

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
        return BookResponse
                .builder()
                .id(id)
                .isbn(isbn)
                .title(title)
                .author(author)
                .publisher(publisher)
                .edition(edition)
                .publicationYear(publicationYear)
                .build();
    }
}
