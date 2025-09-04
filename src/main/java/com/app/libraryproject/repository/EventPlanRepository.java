package com.app.libraryproject.repository;

import com.app.libraryproject.entity.EventPlan;
import com.app.libraryproject.model.PlanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventPlanRepository extends JpaRepository<EventPlan, Long> {

    Page<EventPlan> findAllByPlanStatus(PlanStatus status, Pageable pageable);
}
