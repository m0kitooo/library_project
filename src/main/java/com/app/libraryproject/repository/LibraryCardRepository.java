package com.app.libraryproject.repository;

import com.app.libraryproject.entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LibraryCardRepository extends JpaRepository<LibraryCard, Long> {
    @Query(
            "SELECT l FROM LibraryCard l " +
            "WHERE l.member.id = :memberId " +
            "AND l.deactivationDate IS NULL " +
            "AND l.expiryDate > CURRENT_DATE "
    )
    Optional<LibraryCard> findActiveCardByMemberId(@Param("memberId") Long memberId);
}
