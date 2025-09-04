package com.app.libraryproject.repository;

import com.app.libraryproject.entity.LibraryPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibraryPaymentRepository extends JpaRepository<LibraryPayment, Long> {
    List<LibraryPayment> findByTransactionNameContainingIgnoreCase(String transactionName);
    @Query("SELECT l FROM LibraryPayment l WHERE " +
            "(LOWER(l.transactionName) LIKE LOWER(CONCAT('%', :phrase, '%')) OR " +
            "LOWER(l.invoiceNumber) LIKE LOWER(CONCAT('%', :phrase, '%')))")
    List<LibraryPayment> findByPhrase(@Param("phrase") String phrase);
}
