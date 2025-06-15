package com.app.libraryproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "disposed_books")
public class DisposedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String author;

    private String description;

    @Column(name = "quantity_to_dispose")
    private Integer quantityToDispose;

    @Column(name = "date_added", nullable = false)
    private LocalDate dateAdded;

    @ManyToOne
    @JoinColumn(name = "original_book_id")
    private Book originalBook;
}