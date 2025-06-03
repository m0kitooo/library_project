package com.app.libraryproject.repository;

import com.app.libraryproject.entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryCardRepository extends JpaRepository<LibraryCard, Long> {
}
