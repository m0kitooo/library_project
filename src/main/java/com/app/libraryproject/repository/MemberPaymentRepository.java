package com.app.libraryproject.repository;

import com.app.libraryproject.entity.MemberPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPaymentRepository extends JpaRepository<MemberPayment, Long> {
}