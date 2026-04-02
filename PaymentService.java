package com.project.BTS.service;

import org.springframework.stereotype.Service;

import com.project.BTS.dto.CreateOrderRequest;
import com.project.BTS.dto.VerifyPaymentRequest;
import com.project.BTS.entity.Booking;
import com.project.BTS.entity.Payment;
import com.project.BTS.entity.ShowSeat;
import com.project.BTS.repository.PaymentRepository;
import com.project.BTS.repository.ShowSeatRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PaymentService {
	
	@Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;
    
    
    @Autowired
    private PaymentRepository paymentRepository;
 
    private final ShowSeatRepository showSeatRepository;

    public Map<String, Object> createOrder(CreateOrderRequest request) throws Exception {

        RazorpayClient client = new RazorpayClient(keyId, keySecret);

        List<ShowSeat> seats = showSeatRepository.findByIdIn(request.getShowSeatIds());
//        for (ShowSeat s : seats) {
//            if (!"AVAILABLE".equals(s.getStatus())) {
//                throw new RuntimeException("Some seats already booked/locked");
//            }
//        }
        double total = 0;
        for (ShowSeat s : seats) {
            total += s.getPrice();
        }

        org.json.JSONObject options = new org.json.JSONObject();
        options.put("amount", (int)(total * 100));
        options.put("currency", "INR");
        options.put("receipt", "txn_" + System.currentTimeMillis());

        Order order = client.orders.create(options);
        System.out.println("oid"+order.get("id")+" "+total);
        return Map.of(
                "orderId", order.get("id"),
                "amount", total
        );
    }

    public boolean verifyPayment(String orderId, String paymentId, String signature) {

        String data = orderId + "|" + paymentId;

        String generatedSignature = org.apache.commons.codec.digest.HmacUtils
                .hmacSha256Hex(keySecret, data);
        System.out.println(generatedSignature.equals(signature));
        return generatedSignature.equals(signature);
    }
    
   

    public void savePayment(VerifyPaymentRequest request, Booking booking) {

        Payment payment = Payment.builder()
                .razorpayOrderId(request.getRazorpayOrderId())
                .razorpayPaymentId(request.getRazorpayPaymentId())
                .razorpaySignature(request.getRazorpaySignature())
                .amount(booking.getTotalAmount())
                .status("SUCCESS")
                .booking(booking)
                .build();
        System.out.println("save");
        paymentRepository.save(payment);
    }
    
    public void saveFailedPayment(VerifyPaymentRequest request) {

        Payment payment = Payment.builder()
                .razorpayOrderId(request.getRazorpayOrderId())
                .razorpayPaymentId(request.getRazorpayPaymentId())
                .razorpaySignature(request.getRazorpaySignature())
                .status("FAILED")
                .build();
        System.out.println("failed");
        paymentRepository.save(payment);
    }
    @PostConstruct
    public void test() {
        System.out.println("KEY ID = " + keyId);
    }

}
