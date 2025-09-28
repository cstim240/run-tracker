package com.tim.runtracker.repository;

import com.tim.runtracker.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * The repository talks to the database (middleman API), this interface extends the JPA repository
 * This findByDate method signature is actually recognized by Spring Data JPA, generating the implementation automatically: it generates a SQL: SELECT * FROM run WHERE data = ?
 * <Run, Long> are genetics,
 */
@Repository
public interface RunRepository extends JpaRepository<Run, Long> {
    List<Run> findByDate(LocalDate date);
}


