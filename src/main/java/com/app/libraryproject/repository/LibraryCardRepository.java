// LibraryCardRepository.java
package com.app.libraryproject.repository;

import com.app.libraryproject.entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryCardRepository extends JpaRepository<LibraryCard, Long> {

    @Query("SELECT lc FROM LibraryCard lc " +
            "WHERE lc.member.id = :memberId " +
            "AND lc.expiryDate >= :today")
    Optional<LibraryCard> findActiveCardByMemberId(@Param("memberId") Long memberId,
                                                   @Param("today") LocalDate today);

    @Query("SELECT lc FROM LibraryCard lc " +
            "WHERE lc.member.id = :memberId " +
            "AND lc.expiryDate < :today")
    List<LibraryCard> findInactiveCardsByMemberId(@Param("memberId") Long memberId,
                                                  @Param("today") LocalDate today);
}

