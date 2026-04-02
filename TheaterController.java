package com.project.BTS.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.project.BTS.dto.TheaterRequest;
import com.project.BTS.dto.UserRequest;
import com.project.BTS.entity.User;
import com.project.BTS.entity.Theater;
import com.project.BTS.service.TheaterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/theaters")
@RequiredArgsConstructor
public class TheaterController {
	
	private final TheaterService theatreService;
	
	@PostMapping("/addtheatre")
	private ResponseEntity<Theater> addtheatre(@RequestBody Theater request,@RequestParam Long adminId,@RequestParam Long cityId)
	{
		return ResponseEntity.ok(theatreService.addTheater(request,adminId,cityId));
	}
	
	@GetMapping
	public ResponseEntity<List<Theater>> getAllTheatre()
	{
		return ResponseEntity.ok(theatreService.getAllTheatre());
	}
	
	@GetMapping("admin/{id}")
	public ResponseEntity<List<Theater>> getTheatreByAdmin(@PathVariable Long id)
	{
		return ResponseEntity.ok(theatreService.getTheatreByAdmin(id));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Theater> getTheatreById(@PathVariable Long id)
	{
		return ResponseEntity.ok(theatreService.getTheatreById(id));
	}
	
	@GetMapping("/city/{cityId}")
	public ResponseEntity<List<Theater>> getTheatreByCity(@PathVariable Long cityId)
	{
		return ResponseEntity.ok(theatreService.getTheatreByCity(cityId));
	}
	
}
