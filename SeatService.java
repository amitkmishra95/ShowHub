package com.project.BTS.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import lombok.Builder;

import com.project.BTS.dto.LoginRequest;
import com.project.BTS.dto.UserRequest;
import com.project.BTS.entity.Seat;
import com.project.BTS.entity.User;
import com.project.BTS.entity.City;
import com.project.BTS.repository.CityRepository;
import com.project.BTS.repository.SeatRepository;
import com.project.BTS.repository.TheaterRepository;
import com.project.BTS.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatService {
	

private final SeatRepository seatRepository;

public Seat addSeat(Seat seat)
{
	return seatRepository.save(seat);
}

public List<Seat> getSeatsByScreen(Long screenId)
{
	return seatRepository.findByScreenId(screenId);
}

public Seat getSeatById(Long id)
{
	return seatRepository.findById(id)
			.orElseThrow(()->new RuntimeException("Seat not found with id: "+id));
}

}
