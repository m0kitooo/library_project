package com.app.libraryproject.repository;

import com.app.libraryproject.entity.LibraryPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryPaymentRepository extends JpaRepository<LibraryPayment, Long> {
    List<LibraryPayment> findByTransactionNameContainingIgnoreCase(String transactionName);
}
