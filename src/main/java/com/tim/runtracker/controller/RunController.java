package com.tim.runtracker.controller;

import com.tim.runtracker.model.Difficulty;
import com.tim.runtracker.model.Run;
import com.tim.runtracker.repository.RunRepository;
import jakarta.validation.Valid;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
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

    // retrieve a run by date. Example /runs/date/2024-02-12
    @GetMapping("/runs/date/{date}")
    public List<Run> getRunByDate(@PathVariable LocalDate date){
        return repository.findByDate(date);
    }

    @GetMapping("runs/date/{difficulty}")
    public List<Run> getRunByDifficulty(@PathVariable Difficulty difficulty){
        return repository.findByDifficulty(difficulty);
    }

    // get 'all-time' significant run statistics, total distance, average pace, fastest pace, farthest run, longest run
    @GetMapping("/runs/stats/all")
    public Map<String, Object> getAllStats() {
        LocalDate wayBackThen = LocalDate.of(1900, 1, 1);
        return getStatsSince(wayBackThen);
    }

    @GetMapping("/runs/stats/week")
    public Map<String, Object> getWeekStats() {
        LocalDate aWeekAgo = LocalDate.now().minusWeeks(1);
        return getStatsSince(aWeekAgo);
    }

    @GetMapping("/runs/stats/month")
    public Map<String, Object> getMonthStats() {
        LocalDate aMonthAgo = LocalDate.now().minusMonths(1);
        // ternary op for null check
        return getStatsSince(aMonthAgo);
    }

    @GetMapping("/runs/stats/year")
    public Map<String, Object> getYearStats() {
        LocalDate aYearAgo = LocalDate.now().minusYears(1);
        return getStatsSince(aYearAgo);
    }

    private Map<String, Object> getStatsSince(LocalDate startDate) {
        Map<String, Object> stats = new HashMap<>();
        System.out.println("Getting stats since: " + startDate);

        stats.put("totalDistance", repository.getTotalDistance(startDate) != null ? repository.getTotalDistance(startDate) : 0.0);
        System.out.println("Total dist: " + repository.getTotalDistance(startDate));
        stats.put("averagePace", repository.getAveragePace(startDate) != null ? repository.getAveragePace(startDate) : 0.0);
        stats.put("fastestPace", repository.getFastestPace(startDate) != null ? repository.getFastestPace(startDate) : 0.0);
        stats.put("farthestRun", repository.getFarthestRun(startDate) != null ? repository.getFarthestRun(startDate) : 0.0);
        stats.put("longestRun", repository.getLongestRun(startDate) != null ? repository.getLongestRun(startDate): 0.0);

        return stats;
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

    @DeleteMapping("/runs")
    public void deleteAllRuns() {
        repository.deleteAll();
    }

}
