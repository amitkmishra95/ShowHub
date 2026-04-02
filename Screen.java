package com.project.BTS.entity;

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
@Table(name="screens")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Screen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private Integer totalSeats;
	
	@Column(name = "row_count")
	private Integer rows;     
	
	@Column(name = "column_count")
	private Integer columns;
	
	@ManyToOne
	@JoinColumn(name="theatre_id",nullable=false)
	private Theater theatre;

}
