package com.project.BTS.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import lombok.Builder;

import com.project.BTS.dto.LoginRequest;
import com.project.BTS.dto.UserRequest;
import com.project.BTS.entity.Movie;
import com.project.BTS.entity.User;
import com.project.BTS.entity.City;
import com.project.BTS.entity.Theater;
import com.project.BTS.repository.CityRepository;
import com.project.BTS.repository.MovieRepository;
import com.project.BTS.repository.TheaterRepository;
import com.project.BTS.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
	

		private final  MovieRepository movieRepository;
		
		public Movie addMovie(Movie movie)
		{
			return movieRepository.save(movie);
		}

		public List<Movie> getAllMovies()
		{
			return movieRepository.findAll();
		}
		
		public Movie getMovieById(Long id)
		{
			return movieRepository.findById(id)
					.orElseThrow(()->new RuntimeException("Movie not found with id : "+id));
		}
		
//		public Movie getMovieByScreenId(Long Screenid)
//		{
//			return movieRepository.findByScreenId(Screenid)
//					.orElseThrow(()->new RuntimeException("Movie not found with id : "+id));
//		}
//		
		public List<Movie> searchMovieByTitle(String title)
		{
			return movieRepository.findByTitleContainingIgnoreCase(title);
		}
		
		public List<Movie> getByGenre(String genre)
		{
			return movieRepository.findByGenre(genre);
		}
		public List<Movie> getByLanguage(String language)
		{
			return movieRepository.findByLanguage(language);
		}
		
		public Movie deleteById(Long id)
		{
		    Movie movie = movieRepository.findById(id)
		            .orElseThrow(() -> new RuntimeException("Movie not found"));

		    movieRepository.delete(movie);

		    return movie;
		}

		

}
