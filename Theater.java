package com.project.BTS.entity;

import com.project.BTS.dto.ScreenRequest;

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
@Table(name="theatres")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Theater {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String address;
	
	@ManyToOne
	@JoinColumn(name="city_id",nullable=false)
	private  City city;

	@ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

	public void setAdmin(Admin admin) { this.admin = admin; }
	public Admin getAdmin() {
		// TODO Auto-generated method stub
		return admin;
	}
}
