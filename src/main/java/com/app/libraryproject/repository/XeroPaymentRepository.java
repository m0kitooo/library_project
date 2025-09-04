package com.app.libraryproject.repository;

import com.app.libraryproject.entity.XeroPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XeroPaymentRepository extends JpaRepository<XeroPayment, Long> {
}
