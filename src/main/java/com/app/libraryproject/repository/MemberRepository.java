package com.app.libraryproject.repository;

import com.app.libraryproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.*;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("""
    SELECT m FROM Member m
    WHERE LOWER(CONCAT(m.name, ' ', m.surname)) LIKE LOWER(CONCAT('%', :fullname, '%'))
    """)
    Page<Member> searchMembersByFullname(@Param("fullname") String fullname, Pageable pageable);

}
