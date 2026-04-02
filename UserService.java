package com.project.BTS.service;
import com.project.BTS.exception.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;
import lombok.Builder;

import com.project.BTS.dto.LoginRequest;
import com.project.BTS.dto.OtpVerifyRequest;
import com.project.BTS.dto.RegisterRequest;
import com.project.BTS.dto.UserRequest;
import com.project.BTS.entity.User;
import com.project.BTS.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
private final  UserRepository userRepository;
private final EmailService emailService;
private final JwtService jwtService;
//register
public User register(UserRequest req)
{
	if(userRepository.existsByEmail(req.getEmail()))
	{
		throw new RuntimeException("Email already exists: "+req.getEmail());
	}
	
	User user = User.builder()
	        .name(req.getName())
	        .email(req.getEmail())
	        .password(req.getPassword())
	        .phone(req.getPhone())
	        .build();
	return userRepository.save(user);
}
public Map<String, String> sendOtp(RegisterRequest req) {

    User existingUser = userRepository.findByEmail(req.getEmail()).orElse(null);

    String otp = String.valueOf(100000 + new Random().nextInt(900000));

    if (existingUser != null) {

        if (existingUser.getIsVerified()) {
            throw new RuntimeException("Email already registered");
        }

        existingUser.setOtp(otp);
        existingUser.setOtpExpiry(LocalDateTime.now().plusMinutes(5));

        userRepository.save(existingUser);

        emailService.sendOtp(existingUser.getEmail(), otp);

        return Map.of("message", "OTP resent successfully");
    }

    User user = User.builder()
            .name(req.getName())
            .email(req.getEmail())
            .password(req.getPassword())
            .phone(req.getPhone())
            .isVerified(false)
            .otp(otp)
            .otpExpiry(LocalDateTime.now().plusMinutes(5))
            .build();

    userRepository.save(user);

    emailService.sendOtp(req.getEmail(), otp);

    return Map.of("message", "OTP sent successfully");
}
public User verifyOtp(OtpVerifyRequest request) {

    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!user.getOtp().equals(request.getOtp())) {
        throw new RuntimeException("Invalid OTP");
    }

    if (user.getOtpExpiry().isBefore(LocalDateTime.now())) {
        throw new RuntimeException("OTP expired. please try again. ");
    }

    user.setIsVerified(true);
    user.setOtp(null);
    user.setOtpExpiry(null);

    return userRepository.save(user);
}


//login

public User login(LoginRequest req)
{
	User user = userRepository.findByEmail(req.getEmail())
			.orElseThrow(()->new RuntimeException("User not found with given email: "+req.getEmail()));
	
	if(!user.getPassword().equals(req.getPassword()))
	{
		throw new RuntimeException("Invalid Password");
	}
	if (!user.getIsVerified()) {
        throw new RuntimeException("Please verify OTP first");
    }
	
//	String token = jwtService.generateToken(user.getEmail());
//
//    return Map.of(
//            "token", token,
//            "user", user
//    );
	return user;

}

//get all user

public List<User> getAllUser()
{
	return userRepository.findAll();
}

public User getUserById(Long id)
{
	return userRepository.findById(id)
			.orElseThrow(()->new RuntimeException("User not found :"+id));
}

}
