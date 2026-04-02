//package com.project.BTS.service;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.project.BTS.dto.LoginRequest;
//import com.project.BTS.dto.OtpVerifyRequest;
//import com.project.BTS.dto.RegisterRequest;
//import com.project.BTS.entity.User;
//import com.project.BTS.repository.AdminRepository;
//import com.project.BTS.repository.CityRepository;
//import com.project.BTS.repository.TheaterRepository;
//import com.project.BTS.repository.UserRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class UserAuthService {
//	
//	private final UserRepository userRepository;
//	private final EmailService emailService;
//	
////	public UserAuthService(UserRepository userRepository,
////			EmailService emailService)
////	{
////		this.emailService=emailService;
////		this.userRepository=userRepository;
////		
////	}
//	
//
//	    public String register(RegisterRequest request) {
//
//	        Optional<User> existing = userRepository.findByEmail(request.getEmail());
//	        if (existing.isPresent()) {
//	            throw new RuntimeException("User already exists");
//	        }
//
//	        User user = new User();
//	        user.setName(request.getName());
//	        user.setEmail(request.getEmail());
//	        user.setPassword(request.getPassword());
//	        user.setAuthProvider("LOCAL");
//	        user.setIsVerified(false);
//
//	        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);
//
//	        user.setOtp(otp);
//	        user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
//
//	        userRepository.save(user);
//
//	        emailService.sendOtp(user.getEmail(), otp);
//
//	        return "OTP sent to email";
//	    }
//
//	    public String verifyOtp(OtpVerifyRequest request) {
//
//	        User user = userRepository.findByEmail(request.getEmail())
//	                .orElseThrow(() -> new RuntimeException("User not found"));
//
//	        if (!user.getOtp().equals(request.getOtp())) {
//	            throw new RuntimeException("Invalid OTP");
//	        }
//
//	        if (user.getOtpExpiry().isBefore(LocalDateTime.now())) {
//	            throw new RuntimeException("OTP expired");
//	        }
//
//	        user.setIsVerified(true);
//	        user.setOtp(null);
//	        user.setOtpExpiry(null);
//
//	        userRepository.save(user);
//
//	        return "User verified successfully";
//	    }
//
//	    public User login(LoginRequest request) {
//
//	        User user = userRepository.findByEmail(request.getEmail())
//	                .orElseThrow(() -> new RuntimeException("Invalid email"));
//
//	        if (!user.getIsVerified()) {
//	            throw new RuntimeException("Please verify OTP first");
//	        }
//
//	        if (!user.getPassword().equals(request.getPassword())) {
//	            throw new RuntimeException("Invalid password");
//	        }
//
//	        return user;
//	    }
//	}
//
//
