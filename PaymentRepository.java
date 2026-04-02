package com.project.BTS.repository;

import com.project.BTS.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

//	Payment save(Payment payment);
}