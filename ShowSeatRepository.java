package com.project.BTS.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BTS.entity.Show;
import com.project.BTS.entity.ShowSeat;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

	List<ShowSeat> findByShowId(Long showId);

	List<ShowSeat> findAll();
	List<ShowSeat> findByStatus(String status);
	List<ShowSeat> findByIdIn(List<Long> id);
//	List<ShowSeat> findByShowIdandStatus(Long ShowId,String Status);
	List<ShowSeat> findAllById(Long id);
	List<ShowSeat> findByShowIdAndStatus(Long showId, String status);
	
}