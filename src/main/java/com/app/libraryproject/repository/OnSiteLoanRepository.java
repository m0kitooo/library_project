package com.app.libraryproject.repository;

import com.app.libraryproject.entity.Member;
import com.app.libraryproject.entity.OnSiteLoan;
import com.app.libraryproject.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OnSiteLoanRepository extends JpaRepository<OnSiteLoan, Long> {
    List<OnSiteLoan> findByMemberAndStatus(Member member, LoanStatus status);
    List<OnSiteLoan> findByMember(Member member);
}

