package com.project.BTS.entity;

//package com.project.BTS.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;

    private Double amount;

    private String status; // SUCCESS / FAILED

    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}