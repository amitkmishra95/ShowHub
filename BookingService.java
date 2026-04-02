package com.project.BTS.service;
import com.project.BTS.exception.*;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import lombok.Builder;

import com.project.BTS.dto.BookingRequest;
import com.project.BTS.dto.LoginRequest;
import com.project.BTS.dto.UserRequest;
import com.project.BTS.entity.Booking;
import com.project.BTS.entity.BookingSeat;
import com.project.BTS.entity.Seat;
import com.project.BTS.entity.Show;
import com.project.BTS.entity.ShowSeat;
import com.project.BTS.entity.User;
import com.project.BTS.entity.City;
import com.project.BTS.entity.Theater;
import com.project.BTS.enums.BookingStatus;
import com.project.BTS.repository.BookingRepository;
import com.project.BTS.repository.BookingSeatRepository;
import com.project.BTS.repository.CityRepository;
import com.project.BTS.repository.SeatRepository;
import com.project.BTS.repository.ShowRepository;
import com.project.BTS.repository.ShowSeatRepository;
import com.project.BTS.repository.TheaterRepository;
import com.project.BTS.repository.UserRepository;
//import Session.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {
	
	private final  UserRepository userRepository;
	private final ShowRepository showRepository;
private  final BookingRepository bookingRepository;
private  final BookingSeatRepository bookingseatRepository;
private final ShowService showService;
private final UserService userService;
private final SeatRepository seatRepository;
private final ShowSeatRepository showSeatRepository;
//private  final  HttpRequest httpRequest;
@Transactional
public List<ShowSeat> lockSeats(List<Long> showSeatIds) {
	
	
	
    List<ShowSeat> seats = showSeatRepository.findAllById(showSeatIds);

    for (ShowSeat seat : seats) {

        if (!seat.getStatus().equals("AVAILABLE")) {
            throw new RuntimeException("Seat not available: " + seat.getId());
        }

        seat.setStatus("LOCKED");
        seat.setLockedAt(LocalDateTime.now());
    }

    return showSeatRepository.saveAll(seats);
}
@Transactional
public Booking bookSeats(String email,Long showId, List<Long> showSeatIds) {
//System.out.println(userId+" "+showId+" "+showSeatIds.size());
	 User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("User not found"));
//	User user = (User) httpRequest.getSession().getAttribute("user");
//	 System.out.println(1);
	if (user == null) {
	    throw new RuntimeException("User not logged in");
	}
//	System.out.println(2);
//	String email = user.getEmail();
	    Show show = showRepository.findById(showId)
	            .orElseThrow(() -> new RuntimeException("Show not found"));
    List<ShowSeat> seats = showSeatRepository.findByIdIn(showSeatIds);
//    System.out.println(3);
    for (ShowSeat seat : seats) {
    	System.out.println("showseat "+seat);
        if (!seat.getStatus().equals("LOCKED")) {
            throw new RuntimeException("Seat must be locked before booking");
        }
    }
//    System.out.println(4);
    double total = seats.stream()
            .mapToDouble(ShowSeat::getPrice)
            .sum();
    System.out.println("total "+total);
    Booking booking = new Booking();
    booking.setUserId(user.getId());
//    System.out.println(6); 
    booking.setShow(seats.get(0).getShow());
//    System.out.println(7);
    booking.setTotalAmount(total);
    booking.setStatus("CONFIRMED");
    booking.setCreatedAt(LocalDateTime.now());
//    System.out.println(8);
    booking.setShowSeats(seats);
//    System.out.println("jj");
    Booking savedBooking = bookingRepository.save(booking);

   
    for (ShowSeat seat : seats) {

        seat.setStatus("BOOKED");
        seat.setLockedAt(null);

        showSeatRepository.save(seat);

        BookingSeat bs = new BookingSeat();
        bs.setBooking(savedBooking);
        bs.setShowSeat(seat);

        bookingseatRepository.save(bs);
    }
//    System.out.println("j5");
    return savedBooking;
}

@Transactional
public void cancelBooking(Long bookingId) {

    Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));

    if (!booking.getStatus().equals("CONFIRMED")) {
        throw new RuntimeException("Only confirmed bookings can be cancelled");
    }

    List<BookingSeat> bookingSeats = bookingseatRepository.findAll();

    for (BookingSeat bs : bookingSeats) {
        if (bs.getBooking().getId().equals(bookingId)) {

            ShowSeat seat = bs.getShowSeat();

            seat.setStatus("AVAILABLE");
            seat.setLockedAt(null);

            showSeatRepository.save(seat);
        }
    }

    booking.setStatus("CANCELLED");
    bookingRepository.save(booking);
}
@Scheduled(fixedRate = 60000)
public void releaseExpiredLocks() {

    List<ShowSeat> seats = showSeatRepository.findAll();

    for (ShowSeat seat : seats) {

        if ("LOCKED".equals(seat.getStatus()) &&
            seat.getLockedAt() != null &&
            seat.getLockedAt().isBefore(LocalDateTime.now().minusMinutes(5))) {

            seat.setStatus("AVAILABLE");
            seat.setLockedAt(null);
        }
    }

    showSeatRepository.saveAll(seats);
}
	public List<Seat> getAvailableSeats(Long showId)
	{
		List<ShowSeat> showSeats = showSeatRepository.findByShowIdAndStatus(showId,"AVAILABLE");
		return showSeats.stream().map(ShowSeat::getSeat).toList();
	}
	
	public Booking getBookingById(Long id)
	{
		return bookingRepository.findById(id).orElseThrow(()->new RuntimeException("Booking not found"));
	}
	
	public List<Booking> getBookingByUser(Long userId) {

	    return bookingRepository.findByUserId(userId);
	}
	
}

