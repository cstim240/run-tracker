package com.tim.runtracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

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

    @NotNull @PastOrPresent(message = "input date cannot happen in the future")
    private LocalDate date;
    @NotNull @Positive(message = "distance cannot be negative")
    private Double distance;
    @NotNull @Positive(message = "duration cannot be negative")
    private Integer duration;
    // note: we use Double since it can be null (JPA requires this for entity fields)
    private Double pace;

    // constructors, getters, setters
    public Run(){}

    public Run(LocalDate date, Double distance, Integer duration) {
        this.date = date;
        this.distance = distance;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getPace() {
        return pace;
    }

    @PrePersist
    @PreUpdate
    // these JPA lifecycle annotations refer to the callbacks that occur with every relevant database transaction
    // @ PrePersist runs before making a new run and PreUpdate runs every time we update an existing run
    // lifecycle callbacks are function calls that are called whenever a specific point in an entity's lifecycle happens
    public void calculatePace() {
        if (distance != null && duration != null && distance > 0){
            pace = Math.round((duration/distance) * 100.0) / 100.0;
        }
    }
}
