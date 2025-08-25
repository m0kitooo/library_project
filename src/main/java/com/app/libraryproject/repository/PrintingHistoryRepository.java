package com.app.libraryproject.repository;

import com.app.libraryproject.entity.PrintingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrintingHistoryRepository extends JpaRepository<PrintingHistory, Long> {
}
