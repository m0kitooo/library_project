package com.app.libraryproject.repository;

import com.app.libraryproject.entity.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
    List<BookLoan> findByBookId(Long bookId);
    List<BookLoan> findByBookIdAndArchivedFalse(Long bookId);
    List<BookLoan> findByBookIdAndMemberId(Long bookId, Long memberId);
    List<BookLoan> findByBookIdAndMemberIdAndArchivedFalse(Long bookId, Long memberId);
    @Query("SELECT COUNT(*) FROM BookLoan bl WHERE bl.book.id = :bookId AND bl.archived = false")
    Long countActiveLoansByBookId(@Param("bookId") Long bookId);
}
