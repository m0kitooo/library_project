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
    List<Book> findByArchivedFalse();
    List<Book> findByTitleContainingIgnoreCaseAndArchivedFalse(String title);
    @Transactional
    @Modifying
    @Query(
            "UPDATE Book b SET b.archived = true " +
            "WHERE b.id = :id AND b.archived = false"
    )
    int archive(Long id);
}
