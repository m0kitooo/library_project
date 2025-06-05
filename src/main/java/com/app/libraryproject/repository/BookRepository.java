package com.app.libraryproject.repository;

import com.app.libraryproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIdAndArchivedFalse(Long id);
    Optional<Book> findByIdAndArchivedFalseAndQuantityGreaterThan(Long id, int quantity);
    Optional<Book> findByTitle(String title);
    List<Book> findByTitleContainingIgnoreCase(String title);
}
