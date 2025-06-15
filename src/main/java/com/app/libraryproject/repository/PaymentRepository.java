package com.app.libraryproject.repository;

import com.app.libraryproject.entity.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<BookLoan, Long> {
}
