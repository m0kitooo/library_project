package com.app.libraryproject.repository;

import com.app.libraryproject.entity.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
    Optional<BookLoan> findByBookIdAndMemberId(Long bookId, Long memberId);
    List<BookLoan> findAllByMemberId(Long memberId);
}