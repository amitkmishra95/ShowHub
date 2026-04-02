package com.project.BTS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.BTS.entity.Booking;
import com.project.BTS.entity.City;

public interface BookingRepository extends JpaRepository<Booking,Long>  {
	
	List<Booking> findByUserId(Long userId);
	List<Booking> findByShowId(Long showId);
	
	// all booked seat
//	@Query("Select s.id From Booking b join b.seats s where b.show.id=:showId and b.status='CONFIRMED'")
//	List<Long> findBookedSeatIdsByShowId(@Param("showId") Long showId);

}
