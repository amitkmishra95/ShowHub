package com.project.BTS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BTS.entity.Seat;
import com.project.BTS.entity.User;
import com.project.BTS.entity.City;
import com.project.BTS.entity.Theater;

public interface SeatRepository extends JpaRepository<Seat,Long> {
	
	List<Seat> findByScreenId(Long screenId);
  
	

}
