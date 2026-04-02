package com.project.BTS.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.BTS.dto.LockSeatRequest;
import com.project.BTS.dto.ReleaseSeatRequest;
import com.project.BTS.entity.ShowSeat;
import com.project.BTS.service.ShowSeatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/show-seats")
@RequiredArgsConstructor
public class ShowSeatController {

	private final ShowSeatService showSeatService;
	
	@GetMapping("/{showId}")
	public ResponseEntity<List<ShowSeat>> getAllShowSeat(@PathVariable Long showId)
	{
		return ResponseEntity.ok(showSeatService.getByShow(showId));
	}
	
	@PostMapping("/lock")
	public List<ShowSeat> lockSeats(@RequestBody LockSeatRequest req)
	{
		return showSeatService.lockSeats(req);
	}
	
	@PostMapping("/release")
	public String releaseSeats(@RequestBody ReleaseSeatRequest req)
	{
		 showSeatService.releaseSeats(req);
		 return "Seats released";
	}
}
