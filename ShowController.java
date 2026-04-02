package com.project.BTS.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.BTS.dto.ShowRequest;
import com.project.BTS.entity.Show;
import com.project.BTS.service.ShowService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ShowController {
	
	private final ShowService showService;
	
	
	@PostMapping("/addshow")
    public ResponseEntity<Show> addShow(@RequestBody ShowRequest req) {
        return ResponseEntity.ok(showService.createShow(req));
    }

    @GetMapping
    public ResponseEntity<List<Show>> getAllShows() {
        return ResponseEntity.ok(showService.getAllShow());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable Long id) {
        return ResponseEntity.ok(showService.getShowById(id));
    }
    @GetMapping("/screen/{sid}")
    public ResponseEntity<List<Show>> getShowByScreen(@PathVariable Long sid) {
        return ResponseEntity.ok(showService.getShowByScreen(sid));
    }
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Show>> getShowByMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(showService.getShowByMovie(movieId));
    }

 
    @GetMapping("/movie/{movieId}/date/{date}")
    public ResponseEntity<List<Show>> getShowsByMovieAndDate(
            @PathVariable Long movieId,
            @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)LocalDate date) {

//        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(showService.getShowByMovieAndDate(movieId,date));
    }

//    @GetMapping("/screen/{screenId}")
//    public ResponseEntity<List<Show>> getShowsByScreen(@PathVariable Long screenId) {
//        return ResponseEntity.ok(showService.getShowByScreen(screenId));
//    }

}
