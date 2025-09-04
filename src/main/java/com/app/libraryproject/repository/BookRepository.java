package com.app.libraryproject.repository;

import com.app.libraryproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIdAndArchivedFalse(Long id);
    Optional<Book> findByIdAndArchivedFalseAndQuantityGreaterThan(Long id, int quantity);
    List<Book> findByArchivedFalse();
    List<Book> findByTitleContainingIgnoreCaseAndArchivedFalse(String title);
    List<Book> findByTitleContainingIgnoreCaseAndAuthorIgnoreCaseAndArchivedFalse(String title, String author);
    @Transactional
    @Modifying
    @Query(
            "UPDATE Book b SET b.archived = true " +
            "WHERE b.id = :id AND b.archived = false"
    )
    int archive(@Param("id") Long id);
    @Query(
            "SELECT b.quantity - COALESCE(SIZE(b.bookLoans), 0) " +
            "FROM Book b " +
            "WHERE b.id = :bookId AND b.archived = false"
    )
    int getAvailableBooksQuantityById(@Param("bookId") Long bookId);
    @Query(
			"SELECT b FROM Book b " +
			"WHERE b.id = :bookId AND b.archived = false " +
			"AND (b.quantity - COALESCE(SIZE(b.bookLoans) + SIZE(b.bookReservations), 0)) > 0"
	)
	Optional<Book> findAvailableBookById(@Param("bookId") Long bookId);

    List<Book> findBooksByAuthor(String author);
}
