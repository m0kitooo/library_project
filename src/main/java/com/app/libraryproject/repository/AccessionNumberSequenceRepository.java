package com.app.libraryproject.repository;

import com.app.libraryproject.entity.AccessionNumberSequence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessionNumberSequenceRepository extends JpaRepository<AccessionNumberSequence, Long> {
}
