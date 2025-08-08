package com.app.libraryproject.repository;

import com.app.libraryproject.entity.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
    Optional<BookLoan> findByBookId(Long bookId);
    Optional<BookLoan> findByBookIdAndArchivedFalse(Long bookId);
    Optional<BookLoan> findByBookIdAndMemberId(Long bookId, Long memberId);
    Optional<BookLoan> findByBookIdAndMemberIdAndArchivedFalse(Long bookId, Long memberId);
}
