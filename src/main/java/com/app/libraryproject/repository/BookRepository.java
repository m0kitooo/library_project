package com.app.libraryproject.repository;

import com.app.libraryproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIdAndArchivedFalse(Long id);
    Optional<Book> findByIdAndArchivedFalseAndQuantityGreaterThan(Long id, int quantity);
    Optional<Book> findByTitle(String title);
    List<Book> findByArchivedFalse();
    List<Book> findByTitleContainingIgnoreCase(String title);
    @Transactional
    @Modifying
    @Query(
            "UPDATE Book b SET b.archived = true, b.quantity = b.quantity - 1 " +
                    "WHERE b.id = :id AND b.archived = false AND b.quantity > 0"
    )
    int archiveAndDecrementQuantity(Long id);
}
