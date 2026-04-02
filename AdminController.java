package com.project.BTS.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.BTS.dto.LoginRequest;
import com.project.BTS.entity.Admin;
import com.project.BTS.entity.Movie;
import com.project.BTS.entity.Screen;
import com.project.BTS.entity.Show;
import com.project.BTS.entity.Theater;
import com.project.BTS.entity.User;
import com.project.BTS.repository.AdminRepository;
import com.project.BTS.service.AdminService;
import com.project.BTS.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService adminService;
	
	
	@PostMapping("/login")
	private ResponseEntity<Admin> login(@RequestBody LoginRequest request)
	{
		return ResponseEntity.ok(adminService.login(request));
	}
	
    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie movie) {
        return adminService.addMovie(movie);
    }

   
    @PostMapping("/theaters")
    public Theater addTheater(@RequestBody Theater theater) {
        return adminService.addTheater(theater);
    }

    @PostMapping("/screens")
    public Screen addScreen(@RequestBody Screen screen) {
        return adminService.addScreen(screen);
    }


    @PostMapping("/shows")
    public Show addShow(@RequestBody Show show) {
        return adminService.addShow(show);
    }
	

}
