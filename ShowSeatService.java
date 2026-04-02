package com.project.BTS.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

//import com.project.BTS.Scheduled;
import org.springframework.scheduling.annotation.Scheduled;
import com.project.BTS.dto.LockSeatRequest;
import com.project.BTS.dto.ReleaseSeatRequest;
import com.project.BTS.dto.ShowSeatRequest;
import com.project.BTS.entity.ShowSeat;
import com.project.BTS.repository.MovieRepository;
import com.project.BTS.repository.ScreenRepository;
import com.project.BTS.repository.SeatRepository;
import com.project.BTS.repository.ShowRepository;
import com.project.BTS.repository.ShowSeatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowSeatService {
	
	private final ShowSeatRepository showSeatRepository;
	
	public List<ShowSeat> getByShow(Long showId) {
        return  showSeatRepository.findByShowId(showId);
    }
	
	
	public List<ShowSeat> lockSeats(LockSeatRequest req)
	{
		List<ShowSeat> seats = showSeatRepository.findByIdIn(req.getShowSeatIds());
		System.out.println("inlock");
		for(ShowSeat ss: seats)
		{
			if ("LOCKED".equals(ss.getStatus())) {

			    if (ss.getLockedAt().isBefore(LocalDateTime.now().minusMinutes(5))) {
			        
			        ss.setStatus("AVAILABLE");
			    } else {
			        throw new RuntimeException("Seat already locked");
			    }
			}
		}
		
		for (ShowSeat ss : seats)
		{
		 if(!ss.getStatus().equals("AVAILABLE"))
			{
				throw new RuntimeException("Some Seats are already booked/Locked");
			}
			ss.setStatus("LOCKED");
			ss.setLockedAt(LocalDateTime.now());
		}
//		System.out.println("lockpass");
		return showSeatRepository.saveAll(seats);
		
		
	}
	
	public void releaseSeats(ReleaseSeatRequest req) {
		
		List<ShowSeat> seats = showSeatRepository.findByIdIn(req.getShowSeatIds());
//		System.out.println("release");
		for(ShowSeat ss : seats)
		{
			if(ss.getStatus().equals("LOCKED")) {
				ss.setStatus("AVAILABLE");
				ss.setLockedAt(null);
			}
		}
		showSeatRepository.saveAll(seats);
//		System.out.println("release2");
	}
	
	@Scheduled(fixedRate = 60000) 
	public void releaseExpiredLocks() {

	    List<ShowSeat> seats = showSeatRepository.findAll();
//	    System.out.println("release 3");
	    LocalDateTime now = LocalDateTime.now();

	    for (ShowSeat seat : seats) {

	        if ("LOCKED".equals(seat.getStatus()) && seat.getLockedAt() != null) {

	            if (seat.getLockedAt().isBefore(now.minusMinutes(2))) {

	                seat.setStatus("AVAILABLE");
	                seat.setLockedAt(null);
	            }
	        }
	    }

	    showSeatRepository.saveAll(seats);
//	    System.out.println("release 4");
	}
	public void markAsBooked(List<Long> showSeatIds)
	{
		List<ShowSeat> seats = showSeatRepository.findByIdIn(showSeatIds);
		
		for(ShowSeat ss:seats)
		{
			ss.setStatus("BOOKED");
			ss.setLockedAt(null);
		}
		showSeatRepository.saveAll(seats);
	}

}
