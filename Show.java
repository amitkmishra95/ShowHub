package com.project.BTS.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="shows")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Show {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private LocalDate showDate;
	
	@ManyToOne
	@JoinColumn(name="movie_id",nullable=false)
	private Movie movie;
	
	@ManyToOne
	@JoinColumn(name="screen_id",nullable=false)
	private Screen screen;
	
	@Column(unique=false,nullable=false)
	private LocalDateTime startTime;
	@Column(unique=false,nullable=false)
	private LocalDateTime endTime;
	

	
//	private int ticketPrice;

}
