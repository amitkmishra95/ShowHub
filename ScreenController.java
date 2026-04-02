package com.project.BTS.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.BTS.dto.ScreenRequest;
import com.project.BTS.entity.Screen;
import com.project.BTS.service.ScreenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/screens")
@RequiredArgsConstructor
public class ScreenController {

	private final ScreenService screenService;
	
	@PostMapping("/addScreen")
	public ResponseEntity<Screen> addScreen(@RequestBody ScreenRequest req)
	{
		return ResponseEntity.ok(screenService.addScreen(req));
	}
	
	@GetMapping("/id")
	public ResponseEntity<Screen> getScreenById(@PathVariable Long id)
	{
		return ResponseEntity.ok(screenService.getScreenById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<Screen>> getAllScreens()
	{
		return ResponseEntity.ok(screenService.getAllScrren());
	}
	
	@GetMapping("/theater/{theatreId}")
	public ResponseEntity<List<Screen>> getScreenByTheaterId(@PathVariable Long theatreId)
	{
		return ResponseEntity.ok(screenService.getScreenByTheatre(theatreId));
	}
}
