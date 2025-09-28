package com.tim.runtracker.repository;

import com.tim.runtracker.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * The repository talks to the database, this interface extends the JPA repository
 */
@Repository
public interface RunRepository extends JpaRepository<Run, Long> {
    List<Run> findByDate(LocalDate date);
}


