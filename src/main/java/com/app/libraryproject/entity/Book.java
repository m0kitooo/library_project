package com.app.libraryproject.entity;

import com.app.libraryproject.dto.BookDto;
import jakarta.persistence.*;
import lombok.*;

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
    private long id;

    @Column(nullable = false)
    private String title;

    
//    private Author author;

    private String description;

    public BookDto toBookDto() {
        return new BookDto(title, description);
    }
}
