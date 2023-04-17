package com.booking.bookmyroom.data.model;

import java.time.LocalDateTime;

import com.booking.bookmyroom.utils.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Booking entity to store meeting booking details.
 *
 */
@Data
@Entity
@Table(name = "bookings")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = Constants.START_TIME, nullable = false)
	private LocalDateTime startTime;
	@Column(name = Constants.END_TIME, nullable = false)
	private LocalDateTime endTime;
	@Column(name = "booked_time", nullable = false)
	private LocalDateTime bookingTime;
	@Column(name = "booked_by", nullable = false)
	private String bookedBy;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private MeetingRoom meetingRoom;

}
