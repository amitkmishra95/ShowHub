package com.project.BTS.entity;

import com.project.BTS.enums.SeatType;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="seats")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String seatNumber;

	
	@Column(name = "seat_row")
	private String rowLabel;

	@Column(name = "seat_col")
	private String columnLabel;
	private String seatType;
	
//	@Column(nullable=false)
	@ManyToOne
	@JoinColumn(name="screen_id",nullable=false)
	private Screen screen;
	
	
}
//uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"screen_id", "seat_number"})
//    }