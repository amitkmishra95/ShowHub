package com.project.BTS.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {

	private Long showId;
	private Long userId;
	private Long totalAmount;
    private List<Long> showSeatIds;
}
