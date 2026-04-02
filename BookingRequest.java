package com.project.BTS.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

	private Long userId;
	private Long showId;
	private List<Long> showSeatIds;
	private Long screenId;
	private LocalDate showDate;
	private LocalDateTime starttime;
	private LocalDateTime endtime;
	private Double ticketprice;
	
}
