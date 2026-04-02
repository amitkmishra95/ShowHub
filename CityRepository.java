package com.project.BTS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BTS.entity.Movie;
import com.project.BTS.entity.City;

public interface CityRepository  extends JpaRepository<City,Long> {
	
	List<City> findByname(String name);

}
