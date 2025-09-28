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
    private Integer duration;

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
}
