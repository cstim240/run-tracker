package com.tim.runtracker.controller;

import com.tim.runtracker.model.Run;
import com.tim.runtracker.repository.RunRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class RunController {

    private final RunRepository repository;

    //dependency injection! Spring injects the repository by calling this constructor with the repository (so we don't use 'new' to create one)
    // our controller depends on the repository, spring injects that dependency for you
    public RunController(RunRepository repository) {
        this.repository = repository;
    }

    // GET retrieves existing data
    @GetMapping("/runs")
    public List<Run> getAllRuns() {
        System.out.println("Getting all the runs?");
        return repository.findAll();
    }
    
    // GET mapping which retrieves a specific run by ID
    @GetMapping("/runs/{id}")
    public Run getRun(@PathVariable Long id){
        // throw an exception if id is invalid
        Optional<Run> optionalRun = repository.findById(id);
        return optionalRun.orElseThrow(() ->
            new RuntimeException("Run not found!")
        );
    }

    // retrieve a run by date. Ie. /runs/date/2024-02-12
    @GetMapping("/runs/date/{date}")
    public List<Run> getRunByDate(@PathVariable LocalDate date){
        return repository.findByDate(date);
    }

    // POST adds new data
    @PostMapping("/runs")
    public Run createRun(@Valid @RequestBody Run run){
        return repository.save(run);
    }

    // PUT modifies existing data
    @PutMapping("/runs/{id}")
    public Run updateRun(@PathVariable Long id, @Valid @RequestBody Run run){
        run.setId(id);
        return repository.save(run);
    }

    @DeleteMapping("/runs/{id}")
    public void deleteRun(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
