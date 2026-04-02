package com.project.BTS.dto;

import com.project.BTS.entity.Screen;
import com.project.BTS.enums.SeatType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class SeatRequest {

	
	private String name;
    private Long theatreId;

    private int totalSeats;
    private int rows;

    private int vipCount;
    private int premiumCount;
    private int regularCount;
}
