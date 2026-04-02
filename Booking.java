package com.project.BTS.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.project.BTS.enums.BookingStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="bookings")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; 
    @ManyToMany
    @JoinTable(
        name = "booking_show_seats",
        joinColumns = @JoinColumn(name = "booking_id"),
        inverseJoinColumns = @JoinColumn(name = "show_seat_id")
    )
    private List<ShowSeat> showSeats;
    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    private Double totalAmount;

//    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private String status; // PENDING / CONFIRMED / FAILED

    private LocalDateTime createdAt;
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private Long id;
//	
////	@ManyToOne
////	@JoinColumn(name="user_id",nullable=false)
////	private User user;
//	
//	private Long userId;
//	
//	@ManyToOne
//	@JoinColumn(name="show_id",nullable=false)
//	private Show show;
//	
//	
//	
////	@ManyToOne
////	@JoinTable(name="booking_seats", joinColumns=@JoinColumn(name="booking_id"),
////			inverseJoinColumns=@JoinColumn(name="seat_id"))
////	private List<Seat> seats;
////	
//	@ManyToMany
//	@JoinTable(
//	    name = "booking_seats",
//	    joinColumns = @JoinColumn(name = "booking_id"),
//	    inverseJoinColumns = @JoinColumn(name = "seat_id")
//	)
//	private List<Seat> seats;
//	
//	private Double totalPrice;
//	
//	@Enumerated(EnumType.STRING)
//	private BookingStatus status;
//	
//	private LocalDateTime bookedAt;
//	
//	@PrePersist
//	private void onCreate()
//	{
//		this.bookedAt=LocalDateTime.now();
//		if(this.status==null)
//		{
//			this.status=BookingStatus.CONFIRMED;
//		}
//	}
}
