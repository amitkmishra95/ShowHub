package com.project.BTS.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    private String phone;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password;

    private LocalDateTime createAt;

    @Column(nullable=false)
    private Boolean isVerified = false;

    private String otp;
    private LocalDateTime otpExpiry;
	
	@Column(nullable=false)
	private String authProvider="Local";
	
	private String providerId;
	
	
	@PrePersist
	protected void onCreate()
	{
		this.createAt=LocalDateTime.now();
	}
	
}
