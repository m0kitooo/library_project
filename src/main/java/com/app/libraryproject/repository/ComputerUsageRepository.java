package com.app.libraryproject.repository;

import com.app.libraryproject.entity.ComputerStation;
import com.app.libraryproject.entity.ComputerUsage;
import com.app.libraryproject.model.UsageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComputerUsageRepository extends JpaRepository<ComputerUsage, Long> {
    Optional<ComputerUsage> findByComputerStationAndUsageStatus(ComputerStation station, UsageStatus usageStatus);

    List<ComputerUsage> findByUsageStatus(UsageStatus usageStatus);
}
