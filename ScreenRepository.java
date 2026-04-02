package com.project.BTS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BTS.entity.Screen;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
	
	List<Screen> findByTheatreId(Long theatreId);

   
    Screen findByName(String name);

    Screen findByTheatreIdAndName(Long theatreId, String name);

    long countByTheatre_Id(Long theatreId);

    void deleteByTheatre_Id(Long theatreId);
	

}
