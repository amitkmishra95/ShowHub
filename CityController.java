package com.project.BTS.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.BTS.entity.City;
import com.project.BTS.service.CityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

	private final CityService cityService;
	
	@PostMapping("/addCity")
	public ResponseEntity<City> addCity(@RequestBody City city)
	{
		return ResponseEntity.ok(cityService.saveCity(city));
	}
	
	@GetMapping("/id")
	public ResponseEntity<City> getCity(@PathVariable Long id)
	{
		return ResponseEntity.ok(cityService.getCityById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<City>> getAllCities()
	{
		return ResponseEntity.ok(cityService.getAllCities());
	}
	
//	@GetMapping("/city/{cityId}")
//	public ResponseEntity<List<City>> getCityById(@PathVariable Long cityId)
//	{
//		return ResponseEntity.ok(cityService.getCityById(cityId));
//	}
	
}
