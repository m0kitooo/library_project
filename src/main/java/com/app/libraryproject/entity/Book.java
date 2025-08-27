package com.app.libraryproject.entity;

import com.app.libraryproject.dto.book.BookResponse;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "books")
@Check(constraints = "quantity >= 0")
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

    @Builder.Default
    @OneToMany(mappedBy = "book")
    private List<BookLoan> bookLoans = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "book")
    private List<BookReservation> bookReservations = new ArrayList<>();

    public BookResponse toBookResponse() {
        return BookResponse
                .builder()
                .id(id)
                .isbn(isbn)
                .title(title)
                .author(author)
                .publicationYear(publicationYear)
                .build();
    }

    public boolean isBookLoanedByMember(Long memberId) {
        return bookLoans.stream()
                .anyMatch(bl -> bl.getMember().getId().equals(memberId));
    }
}
