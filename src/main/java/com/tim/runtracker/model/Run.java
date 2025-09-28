package com.tim.runtracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

/**
 * Model/entity/domain layer is one of the layers in our package structure, represents data
 * The @Entity (JPA dependency) annotation indicates this class maps to a database table -- this gets saved into database
 * Tells ORM how to save/load from database, and creates database table structure
 */

@Entity
public class Run {
    @Id @GeneratedValue
    private Long id;
    private LocalDate date;
    private Double distance;

    // constructors, getters, setters
}
