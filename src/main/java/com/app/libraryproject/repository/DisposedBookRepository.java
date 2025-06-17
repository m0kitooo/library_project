package com.app.libraryproject.repository;

import com.app.libraryproject.entity.DisposedBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DisposedBookRepository extends JpaRepository<DisposedBook, Long> {
    Optional<DisposedBook> findByOriginalBookId(Long originalBookId);
}