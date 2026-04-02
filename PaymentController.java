package com.project.BTS.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.BTS.dto.CreateOrderRequest;
import com.project.BTS.dto.VerifyPaymentRequest;
import com.project.BTS.entity.Booking;
import com.project.BTS.service.BookingService;
import com.project.BTS.service.PaymentService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final BookingService bookingService;

    @PostMapping("/process")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest request) throws Exception {
        System.out.println("process");
    	return ResponseEntity.ok(paymentService.createOrder(request));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(
            HttpServletRequest httpRequest,
            @RequestBody VerifyPaymentRequest request
    ) {
    	System.out.println("verify");
//        String email = (String) httpRequest.getAttribute("email");

        boolean valid = paymentService.verifyPayment(
                request.getRazorpayOrderId(),
                request.getRazorpayPaymentId(),
                request.getRazorpaySignature()
        );

        if (!valid) {
        	 paymentService.saveFailedPayment(request);
            throw new RuntimeException("Payment verification failed");
        }

      
        Booking booking = bookingService.bookSeats(request.getEmail(),
                request.getShowId(),
                request.getShowSeatIds()
        );

       
        paymentService.savePayment(request, booking);
        System.out.println("verified");
        return ResponseEntity.ok(booking);
    }
}