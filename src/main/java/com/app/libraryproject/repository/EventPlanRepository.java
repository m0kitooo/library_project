package com.app.libraryproject.repository;

import com.app.libraryproject.entity.EventPlan;
import com.app.libraryproject.model.EventPlanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventPlanRepository extends JpaRepository<EventPlan, Long> {
    @Query("""
    SELECT e FROM EventPlan e
    WHERE (:organizerId IS NULL OR e.organizer.id = :organizerId)
    AND (:status IS NULL OR e.status = :status)
    """)
    Page<EventPlan> findAll(@Param("organizerId") Long organizerId, @Param("status") EventPlanStatus status, Pageable pageable);
}
