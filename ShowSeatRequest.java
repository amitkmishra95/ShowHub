package com.project.BTS.dto;

import java.time.LocalDateTime;

import com.project.BTS.entity.Seat;
import com.project.BTS.entity.Show;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatRequest {

private Long id;

    private Show show;

    private Seat seat;

    private Double price;

    private String status; // AVAILABLE / LOCKED / BOOKED

    private LocalDateTime lockedAt; 
}
