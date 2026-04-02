package com.project.BTS.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BTS.entity.Movie;
import com.project.BTS.entity.User;
import com.project.BTS.entity.Show;
import com.project.BTS.entity.Theater;

public interface ShowRepository extends JpaRepository<Show,Long> {
	
	List<Show> findAll();
	List<Show> findByMovieId(Long movieId);
	List<Show> findByScreen_Theatre_City_Id(Long cityId);
	List<Show> findByScreenId(Long screenId);
	List<Show> findByMovieIdAndShowDate(Long movieId,LocalDate showDate);
	List<Show> findByScreenIdAndShowDate(Long screenId,LocalDate showDate);

}
