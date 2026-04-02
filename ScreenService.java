package com.project.BTS.service;

import com.project.BTS.exception.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import lombok.Builder;

import com.project.BTS.dto.LoginRequest;
import com.project.BTS.dto.ScreenRequest;
import com.project.BTS.dto.UserRequest;
import com.project.BTS.entity.Screen;
import com.project.BTS.entity.Seat;
import com.project.BTS.entity.Theater;
import com.project.BTS.entity.User;
import com.project.BTS.enums.SeatType;
import com.project.BTS.entity.City;
import com.project.BTS.repository.CityRepository;
import com.project.BTS.repository.ScreenRepository;
import com.project.BTS.repository.SeatRepository;
import com.project.BTS.repository.TheaterRepository;
import com.project.BTS.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScreenService {

	private final ScreenRepository screenRepository;
	private final TheaterRepository theatreRepository;
	private final TheaterService theatreService;
	private final SeatRepository seatRepository;

	public Screen addScreen(ScreenRequest request) {

		Theater theater = theatreRepository.findById(request.getTheaterId())
				.orElseThrow(() -> new RuntimeException("Theatre not found"));

		int total = request.getVipCount() + request.getPremiumCount() + request.getRegularCount();
		if (total != request.getTotalSeats()) {
			throw new RuntimeException("Seat count mismatch");
		}

		Screen screen = new Screen();
		screen.setName(request.getName());
		screen.setTotalSeats(request.getTotalSeats());
		screen.setRows(request.getRows());
		screen.setColumns(request.getColumns());
		screen.setTheatre(theater);

		Screen savedScreen = screenRepository.save(screen);

		generateSeats(savedScreen, request);

		return savedScreen;
	}

	private void generateSeats(Screen screen, ScreenRequest request) {

		int rows = request.getRows();
		int totalSeats = request.getTotalSeats();

		int cols = totalSeats / rows;

		int vipLeft = request.getVipCount();
		int premiumLeft = request.getPremiumCount();
		int regularLeft = request.getRegularCount();

		List<Seat> seats = new ArrayList<>();

		for (int i = 0; i < rows; i++) {

			char rowLabel = (char) ('A' + i);

			for (int j = 1; j <= cols; j++) {

				String seatType;

				if (vipLeft > 0) {
					seatType = "VIP";
					vipLeft--;
				} else if (premiumLeft > 0) {
					seatType = "PREMIUM";
					premiumLeft--;
				} else {
					seatType = "REGULAR";
					regularLeft--;
				}

				String seatNumber = rowLabel + String.valueOf(j);

				Seat seat = Seat.builder().seatNumber(seatNumber).rowLabel(String.valueOf(rowLabel))
						.columnLabel(String.valueOf(j)).seatType(seatType).screen(screen).build();
				seats.add(seat);

			}
		}

		seatRepository.saveAll(seats);
	}

	public List<Screen> getAllScrren() {
		return screenRepository.findAll();
	}

	public Screen getScreenById(Long id) {
		return screenRepository.findById(id).orElseThrow(() -> new RuntimeException("Screen not found with id: " + id));
	}

	public List<Screen> getScreenByTheatre(Long theatreId) {
		return screenRepository.findByTheatreId(theatreId);
	}

	

}
