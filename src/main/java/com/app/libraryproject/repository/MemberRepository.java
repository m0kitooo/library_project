package com.app.libraryproject.repository;

import com.app.libraryproject.entity.Book;
import com.app.libraryproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberById(Long id);
    @Query("""
    SELECT m FROM Member m
    WHERE LOWER(CONCAT(m.name, ' ', m.surname)) LIKE LOWER(CONCAT('%', :fullName, '%'))
    """)
    Page<Member> searchMembersByFullName(@Param("fullName") String fullName, Pageable pageable);
    boolean existsByPesel(String pesel);
    @Query("SELECT m FROM Member m WHERE " +
           "(LOWER(m.name) LIKE LOWER(CONCAT('%', :phrase, '%')) OR " +
           "LOWER(m.surname) LIKE LOWER(CONCAT('%', :phrase, '%'))) OR " +
           "LOWER(CONCAT(m.name, ' ', m.surname)) LIKE LOWER(CONCAT('%', :phrase, '%'))")
    List<Member> findByPhrase(@Param("phrase") String phrase);
}
