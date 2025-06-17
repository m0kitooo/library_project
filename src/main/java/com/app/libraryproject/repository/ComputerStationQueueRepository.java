package com.app.libraryproject.repository;

import com.app.libraryproject.entity.ComputerStationQueue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComputerStationQueueRepository extends JpaRepository<ComputerStationQueue, Long> {
    List<ComputerStationQueue> getComputerStationQueueByComputerStationIdOrderByExceptedEndDesc(Long computerStationId);
    Optional<ComputerStationQueue> getComputerStationQueueByMemberId(Long memberId);
}
