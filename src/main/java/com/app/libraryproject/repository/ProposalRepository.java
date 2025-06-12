package com.app.libraryproject.repository;

import com.app.libraryproject.entity.Proposal;
import com.app.libraryproject.model.ProposalStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.*;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    @Query("SELECT p FROM Proposal p WHERE (:status IS NULL OR p.status = :status)")
    Page<Proposal> findAll(@Param("status") ProposalStatus status, Pageable pageable);
}
