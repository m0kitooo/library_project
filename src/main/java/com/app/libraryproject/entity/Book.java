package com.app.libraryproject.entity;

import com.app.libraryproject.dto.book.BookResponse;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

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

    @Column(nullable = false)
    private String title;

    private String author;

    @Lob
    private String description;

    private Integer quantity;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean archived;

    @Builder.Default
    @OneToMany(mappedBy = "book")
    private List<BookLoan> bookLoans = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "book")
    private List<BookReservation> bookReservations = new ArrayList<>();

    public BookResponse toBookResponse() {
        return new BookResponse(id, title, author, description, quantity);
    }

    public boolean isAvailable() {
        return !archived && quantity > 0 && quantity > (bookReservations.size() + bookLoans.size());
    }

    public boolean isBookLoanedByMember(Long memberId) {
        return bookLoans.stream()
                .anyMatch(bl -> bl.getMember().getId().equals(memberId));
    }
}
