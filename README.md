# Run Tracker API

A Spring Boot REST API for tracking running activities with automatic pace calculation and time-based statistics.

## Overview

This is a backend application designed to help runners log and analyze their running data. The app automatically calculates pace from distance and duration, and provides statistics across different time periods (week, month, year, all-time).

**Current Status:** Backend API complete. Frontend planned for future development to create a full-stack application.

## Tech Stack

### Core Dependencies
- **Spring Boot 3.5.6** - Application framework
- **Java 17** - Programming language
- **Maven** - Build automation and dependency management

### Key Dependencies
- **Spring Web** - REST API endpoints and HTTP request handling
- **Spring Data JPA** - Database operations and repository pattern
- **H2 Database** - File-based database for persistent storage
- **Spring Boot Validation** - Input validation using Jakarta Bean Validation

## Project Structure
src/main/java/com/tim/runtracker/
...

## Features

### CRUD Operations
- **Create** runs with date, distance, and duration
- **Read** all runs or specific run by ID
- **Update** existing runs
- **Delete** individual runs or all runs

### Automatic Calculations
- Pace automatically calculated on save/update using JPA lifecycle callbacks (`@PrePersist`, `@PreUpdate`)
- Pace = duration ÷ distance (minutes per mile)

### Statistics Endpoints
Time-based statistics with automatic date filtering:
- `/runs/stats/all` - All-time statistics
- `/runs/stats/week` - Last 7 days
- `/runs/stats/month` - Last 30 days
- `/runs/stats/year` - Last 365 days

Each endpoint returns:
- Total distance
- Average pace
- Fastest pace
- Farthest run
- Longest run (by duration)

### Validation
- Date cannot be in the future (`@PastOrPresent`)
- Distance must be positive (`@Positive`)
- Duration must be positive (`@Positive`)
- All fields required (`@NotNull`)

## API Endpoints

### Runs
GET /runs - Get all runs
GET /runs/{id} - Get specific run
POST /runs - Create new run