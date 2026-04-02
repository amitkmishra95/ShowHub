package com.project.BTS.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.project.BTS.entity.Theater;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScreenRequest {
	
    private Long id;
	
    private Integer vipCount;
    private Integer premiumCount;
    private Integer regularCount;
	private String name;
	private Integer rows;
	private Integer columns;
	private Long theaterId;
	public Integer totalSeats;
	

}
