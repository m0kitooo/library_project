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
//    Optional<Book> findByIdAndArchivedFalseAndQuantityGreaterThan(Long id, int quantity);
    List<Book> findByArchivedFalse();
    List<Book> findByTitleContainingIgnoreCaseAndArchivedFalse(String title);
    List<Book> findByTitleContainingIgnoreCaseAndAuthorIgnoreCaseAndArchivedFalse(String title, String author);
    @Query("SELECT b FROM Book b WHERE " +
           "(LOWER(b.title) LIKE LOWER(CONCAT('%', :phrase, '%')) OR " +
           "LOWER(b.author) LIKE LOWER(CONCAT('%', :phrase, '%'))) AND b.archived = false")
    List<Book> findByPhrase(@Param("phrase") String phrase);
    @Transactional
    @Modifying
    @Query(
            "UPDATE Book b SET b.archived = true " +
            "WHERE b.id = :id AND b.archived = false"
    )
    int archive(@Param("id") Long id);
//    @Query(
//            "SELECT b.quantity - COALESCE(SIZE(b.bookLoans), 0) " +
//            "FROM Book b " +
//            "WHERE b.id = :bookId AND b.archived = false"
//    )
//    int getAvailableBooksQuantityById(@Param("bookId") Long bookId);
//    @Query(
//			"SELECT b FROM Book b " +
//			"WHERE b.id = :bookId AND b.archived = false " +
//			"AND (b.quantity - COALESCE(SIZE(b.bookLoans) + SIZE(b.bookReservations), 0)) > 0"
//	)
    @Query(
            "SELECT b FROM Book b " +
            "WHERE b.id = :bookId AND b.archived = false AND b.bookLoan is NULL AND b.bookReservation is NULL"
    )
	Optional<Book> findAvailableBookById(@Param("bookId") Long bookId);

    List<Book> findBooksByAuthor(String author);
}
