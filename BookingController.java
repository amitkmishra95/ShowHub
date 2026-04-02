package com.project.BTS.controller;

import java.util.List;

import org.bouncycastle.asn1.ocsp.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.BTS.dto.BookingRequest;
import com.project.BTS.entity.Booking;
import com.project.BTS.entity.Seat;
import com.project.BTS.entity.ShowSeat;
import com.project.BTS.entity.User;
import com.project.BTS.service.AdminService;
import com.project.BTS.service.BookingService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

	    private final BookingService bookingService;

	   
//
//	    @PostMapping("/confirm")
//	    public Booking bookSeats(
//	            @RequestBody BookingRequest req) {
////System.out.println(req.get(User.getId()))
//	        return bookingService.bookSeats(req.getUserId(),req.getShowId(), req.getShowSeatIds());
//	    }

	    @PutMapping("/cancel/{bookingId}")
	    public String cancelBooking(@PathVariable Long bookingId) {
	        bookingService.cancelBooking(bookingId);
	        return "Booking cancelled successfully";
	    }
	
	
	@GetMapping("show/{showId}/available-seats")
	public ResponseEntity<List<Seat>> getAvailableSeats(@PathVariable Long showId)
	{
		return ResponseEntity.ok(bookingService.getAvailableSeats(showId));
	}
	
	
	@GetMapping("/id")
	public ResponseEntity<Booking> getBookingById(@PathVariable Long id)
	{
		return ResponseEntity.ok(bookingService.getBookingById(id));
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Booking>> getBookingByUser(@PathVariable Long userId)
	{
		return ResponseEntity.ok(bookingService.getBookingByUser(userId));
	}

}

