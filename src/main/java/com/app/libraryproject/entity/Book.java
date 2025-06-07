package com.app.libraryproject.entity;

import com.app.libraryproject.dto.book.BookResponse;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

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

    private String description;

    private Integer quantity;

    @Builder.Default
    private Boolean archived = false;

    @OneToMany
    private List<BookReservation> bookReservations;

    public BookResponse toBookResponse() {
        return new BookResponse(id, title, author, description);
    }
}
