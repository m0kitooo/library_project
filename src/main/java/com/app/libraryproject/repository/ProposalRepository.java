package com.app.libraryproject.repository;

import com.app.libraryproject.entity.Proposal;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    @Query("SELECT p FROM Proposal p WHERE (:status IS NULL OR p.status = :status)")
    List<Proposal> findAll(@Param("status") Integer status, Pageable pageable);
}
