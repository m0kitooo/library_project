package com.app.libraryproject.repository;

import com.app.libraryproject.entity.ComputerStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerStationRepositiry extends JpaRepository<ComputerStation, Long> {
}
