package com.app.libraryproject.repository;

import com.app.libraryproject.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    Optional<Status> findByName(String name);
}

