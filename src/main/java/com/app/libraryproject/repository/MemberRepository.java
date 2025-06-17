package com.app.libraryproject.repository;

import com.app.libraryproject.entity.LibraryCard;
import com.app.libraryproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByLibraryCardId(Long libraryCardId);
}
