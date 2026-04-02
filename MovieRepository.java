package com.project.BTS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BTS.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie,Long> {
	
	
	List<Movie> findByGenre(String genre);
	List<Movie> findByLanguage(String language);
	List<Movie> findByTitleContainingIgnoreCase(String title);
	boolean existsById(Long Id);

}
