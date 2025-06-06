package com.app.libraryproject.repository;

import com.app.libraryproject.entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LibraryCardRepository extends JpaRepository<LibraryCard, Long> {
    @Query(
            "SELECT l FROM LibraryCard l " +
                    "WHERE " +
                    "l.member.id =: memberId AND" +
                    " l.deactivationDate = null AND" +
                    " l.expiryDate > local date "
    )
    Optional<LibraryCard> findActiveCardByMemberId(Long memberId);
}
