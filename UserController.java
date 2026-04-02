package com.project.BTS.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.BTS.dto.LoginRequest;
import com.project.BTS.dto.OtpVerifyRequest;
import com.project.BTS.dto.RegisterRequest;
import com.project.BTS.dto.UserRequest;
import com.project.BTS.entity.User;
import com.project.BTS.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/send-otp")
    public ResponseEntity<Map<String,String>> sendOtp(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.sendOtp(request));
    }
	@PostMapping("/verify-otp")
    public ResponseEntity<User> verifyOtp(@RequestBody OtpVerifyRequest request) {
        return ResponseEntity.ok(userService.verifyOtp(request));
    }
	
	@PostMapping("/register")
	private ResponseEntity<User> register(@RequestBody UserRequest request)
	{
		return ResponseEntity.ok(userService.register(request));
	}
	
	@PostMapping("/login")
	private ResponseEntity<User> login(@RequestBody LoginRequest request)
	{
		return ResponseEntity.ok(userService.login(request));
	}
	
	@PostMapping
	private ResponseEntity<List<User>> getAllUsers()
	{
		return ResponseEntity.ok(userService.getAllUser());
	}
	
	@PostMapping("/id")
	private ResponseEntity<User> getUserById(@PathVariable Long id)
	{
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
}
