package com.tim.runtracker.repository;

import com.tim.runtracker.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    // The query language is JPQL (Java Persistence Query Language), it requires aliases. It uses entities rather than directly using database tables.
    @Query("SELECT SUM(r.distance) FROM Run r")
    Double getTotalDistance();

    @Query("SELECT AVG(r.pace) FROM Run r")
    Double getAveragePace();

    @Query("SELECT MAX(r.distance) FROM Run r")
    Double getFarthestRun();

    @Query("SELECT MAX(r.duration) FROM Run r")
    Double getLongestRun();
}


