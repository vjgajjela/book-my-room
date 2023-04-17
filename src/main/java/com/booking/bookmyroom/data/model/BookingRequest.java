package com.booking.bookmyroom.data.model;

import java.time.LocalDateTime;

import com.booking.bookmyroom.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Booking request pojo 
 *
 */
@Data
public class BookingRequest {

	@JsonProperty
	@JsonFormat(pattern = Constants.DATE_PATTERN)
	private LocalDateTime startTime;

	@JsonProperty
	@JsonFormat(pattern = Constants.DATE_PATTERN)
	private LocalDateTime endTime;

	@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
	private int capacity;
}
