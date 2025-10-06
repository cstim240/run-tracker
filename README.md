# Run Tracker API

A Spring Boot REST API for tracking running activities with automatic pace calculation and time-based statistics.

## Overview

This is a backend application designed to help runners log and analyze their running data. The app automatically calculates pace from distance and duration, and provides statistics across different time periods (week, month, year, all-time).

**Current Status:** Backend API in progress. Frontend planned for future development to create a full-stack application.

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
```
├── RunTrackerApplication.java    # Main application entry point
├── controller/
│   └── RunController.java         # REST endpoints for runs and statistics
├── model/
│   └── Run.java                   # Run entity with validation and lifecycle callbacks
└── repository/
└── RunRepository.java         # Data access layer with custom queries
src/main/resources/
└── application.properties         # Database and H2 console configuration
```


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

PUT /runs/{id} - Modify/Update run by id

DELETE /runs/{id} - Delete a specific run by id

DELETE /runs - Delete all runs

### Statistics
GET /runs/stats - All-time statistics

GET /runs/stats/week - Weekly statistics

GET /runs/stats/month - Monthly statistics

GET /runs/stats/year - Yearly statistics

## Database 
**H2 File-based Database**
- Data persists between application restarts
- Location `./data/runtrackerDB.mv.db`
- H2 Console accessible at: `http://localhost:8080/h2`
- Auto-creates tables from entity classes (`ddl-auto=update`)


## Things I've learned
- Project structure and package organization (controller, model, repository layers)
- Dependency injection and the Spring container
- Auto-configuration and application properties
- Component scanning and bean lifecycle

### Spring Data JPA
- Entity mapping with `@Entity` and field annotations
- Repository pattern with `JpaRepository`
- Custom queries using JPQL with `@Query`
- Method naming conventions for query generation
- Lifecycle callbacks (`@PrePersist`, `@PreUpdate`)

### REST API Development
- RESTful endpoint design
- HTTP methods (GET, POST, PUT, DELETE)
- Request/response handling with `@RestController`
- Path variables and request parameters
- JSON serialization/deserialization

### Database Concepts
- In-memory vs file-based databases
- SQL vs JPQL (entity-based queries)
- Database auto-increment IDs and sequences
- H2 database console for debugging

### Validation & Error Handling
- Jakarta Bean Validation annotations
- Input validation with `@Valid`
- Null checking and defensive programming

### Development Practices
- Refactoring to reduce code duplication (DRY)
- Private helper methods vs public endpoints
- Testing with IntelliJ HTTP Client
- Version control with Git

## Current Objectives
- [x] Complete CRUD operations
- [x] Implement automatic pace calculation
- [x] Add time-based statistics
- [x] Implement validation
- [x] Switch to persistent database
- [ ] Add more fields (location, weather, notes, difficulty)
- [ ] Implement filtering and sorting on GET /runs
- [ ] Add proper error handling with custom exceptions

### Long Term
- [ ] Build a frontend (HTML/CSS/JavaScript or React)
- [ ] Deploy to cloud platform
- [ ] Add user authentication
- [ ] Switch to PostgreSQL for production
- [ ] Implement data visualization (charts/graphs)
- [ ] Add goals and progress tracking

## Running the Application

1. Clone the repository
2. Ensure Java 17+ is installed
3. Run `./mvnw spring-boot:run` (Mac/Linux) or `mvnw.cmd spring-boot:run` (Windows)
4. Access API at `http://localhost:8080`
5. Access H2 Console at `http://localhost:8080/h2`

## Example Usage

**Create a run:**
```http
POST http://localhost:8080/runs
Content-Type: application/json

{
  "date": "2025-10-04",
  "distance": 5.0,
  "duration": 40
}
```

### Get Weekly Stats
GET http://localhost:8080/runs/stats/week


### Future Goals
- Personal records tracking
- Training plans and workout types
- Export Data (CSV, JSON)
- Mobile app
- Social Features