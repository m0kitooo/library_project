package com.app.libraryproject.entity;

import com.app.libraryproject.dto.BookResponse;
import com.app.libraryproject.model.Author;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

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


//    private Author author;

    private String description;

    private Integer quantity;

    @Builder.Default
    private Boolean archived = false;

    public BookResponse toBookResponse() {
        return new BookResponse(id, title, description);
    }
}
