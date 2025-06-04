package com.app.libraryproject.repository;

import com.app.libraryproject.entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryCardRepository extends JpaRepository<LibraryCard, Long> {
    Optional<LibraryCard> findByCardNumber(String cardNumber);
}