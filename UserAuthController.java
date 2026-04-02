//package com.project.BTS.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.project.BTS.dto.*;
//import com.project.BTS.service.UserAuthService;
//
//@RestController
//@RequestMapping("/api/user/auth")
//public class UserAuthController {
//
//    private final UserAuthService userAuthService;
//
//    public UserAuthController(UserAuthService userAuthService)
//    {
//    	this.userAuthService=userAuthService;
//    }
//    
//    // ✅ Register
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
//        return ResponseEntity.ok(userAuthService.register(request));
//    }
//
//    // ✅ Verify OTP
//    @PostMapping("/verify-otp")
//    public ResponseEntity<?> verifyOtp(@RequestBody OtpVerifyRequest request) {
//        return ResponseEntity.ok(userAuthService.verifyOtp(request));
//    }
//
//    // ✅ Login
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//        return ResponseEntity.ok(userAuthService.login(request));
//    }
//}