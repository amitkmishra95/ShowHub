package com.project.BTS.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.BTS.entity.Movie;
import com.project.BTS.service.MovieService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
	
	private final MovieService movieService;
//	public MovieController(MovieRepository movieRepository) {
//        this.movieRepository = movieRepository;
//    }
	@PostMapping("/addMovie")
	public ResponseEntity<Movie> addMovie(@RequestBody Movie req)
	{
		return ResponseEntity.ok(movieService.addMovie(req));
	}
	
	@GetMapping
	public ResponseEntity<List<Movie>> getAllMovies(){
		return ResponseEntity.ok(movieService.getAllMovies());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable Long id){
		return ResponseEntity.ok(movieService.getMovieById(id));
	}
	
	
	
	@GetMapping("/search")
	public ResponseEntity<List<Movie>> searchMovieByTitle(@RequestParam String title){
		return ResponseEntity.ok(movieService.searchMovieByTitle(title));
	}
	@GetMapping("/genre/{genre}")
	public ResponseEntity<List<Movie>> getByGenre(@PathVariable String genre){
		return ResponseEntity.ok(movieService.getByGenre(genre));
	}
	
	@GetMapping("/genre/{language}")
	public ResponseEntity<List<Movie>> getByLanguage(@PathVariable String language){
		return ResponseEntity.ok(movieService.getByLanguage(language));
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(
            @PathVariable Long id,
            @RequestBody Movie updatedMovie) {

        Movie movie = movieService.getMovieById(id);

        
        movie.setTitle(updatedMovie.getTitle());
        movie.setGenre(updatedMovie.getGenre());
        movie.setLanguage(updatedMovie.getLanguage());
        movie.setDurationMinutes(updatedMovie.getDurationMinutes());

        return ResponseEntity.ok(movieService.addMovie(movie)); 
    }

    // ✅ DELETE Movie
    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.deleteById(id));
    }
}
