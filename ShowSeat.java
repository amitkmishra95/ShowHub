package com.project.BTS.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"show"})
@Table(name = "show_seats")
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    
    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    
    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String status; // AVAILABLE / LOCKED / BOOKED

    private LocalDateTime lockedAt; // timestamp for expiry

   
}