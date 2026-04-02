package com.project.BTS.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.project.BTS.entity.Movie;
import com.project.BTS.entity.Screen;

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
public class ShowRequest {
	
private Long id;
	
	
	private LocalDate showDate;
	
	private Movie movie;

	private Screen screen;
	private Long movieId;     // ✅ correct
    private Long screenId; 
	
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private double vipPrice;
    private double premiumPrice;
    private double regularPrice;

}
