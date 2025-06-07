package com.app.libraryproject.repository;

import com.app.libraryproject.entity.BookDisposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDisposalRepository extends JpaRepository<BookDisposal, Long> {
}
