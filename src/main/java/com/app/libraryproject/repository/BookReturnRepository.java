package com.app.libraryproject.repository;

import com.app.libraryproject.entity.BookReturn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReturnRepository extends JpaRepository<BookReturn, Long> {
}
