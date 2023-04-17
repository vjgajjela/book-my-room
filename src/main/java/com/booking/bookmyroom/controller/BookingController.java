package com.booking.bookmyroom.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.bookmyroom.data.model.Booking;
import com.booking.bookmyroom.data.model.BookingRequest;
import com.booking.bookmyroom.data.model.MeetingRoom;
import com.booking.bookmyroom.exceptions.BadRequestException;
import com.booking.bookmyroom.exceptions.BookingException;
import com.booking.bookmyroom.service.BookingService;
import com.booking.bookmyroom.service.ValidationService;
import com.booking.bookmyroom.utils.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/v1/meeting-rooms")
public class BookingController {

	@Autowired
	ValidationService validationService;

	@Autowired
	BookingService bookingService;

	@Operation(summary = "Get Available Meeting Rooms", description = "Returns a list of available meeting rooms based on the provided start and end time.", tags = {
			"Meeting Rooms" })
	@GetMapping
	public ResponseEntity<List<MeetingRoom>> getAvailableMeetingRooms(
			@RequestParam(Constants.START_TIME) @DateTimeFormat(pattern = Constants.DATE_PATTERN) @Parameter(description = "Meeting start time ", example = "2023-04-16T18:30") LocalDateTime startTime,
			@RequestParam(Constants.END_TIME) @DateTimeFormat(pattern = Constants.DATE_PATTERN) @Parameter(description = "Meeting end time ", example = "2023-04-16T18:45") LocalDateTime endTime)
			throws BadRequestException, BookingException {
		// validating request
		validationService.validateDateAndTime(startTime, endTime);
		// checking if the request falls within maintenance timings
		bookingService.checkForMaint(startTime.toLocalTime(), endTime.toLocalTime());
		// Fetching meeting rooms available
		return ResponseEntity.ok(bookingService.getAvailableMeetingRooms(startTime, endTime));
	}

	@Operation(summary = "Book a Meeting Room", description = "Books an available meeting room based on the provided start time, end time, and capacity.", tags = {
			"Meeting Rooms" })
	@PostMapping("/book")
	public ResponseEntity<Booking> bookMeetingRoom(@RequestBody BookingRequest bookingRequest,
			@RequestHeader(value = Constants.IDMP_KEY) String idempotencyKey)
			throws BadRequestException, BookingException {

		// checking for idempotency
		bookingService.checkIdempotency(idempotencyKey);

		// validating request
		validationService.validateBooking(bookingRequest);

		// checking if the request falls within maintenance timings
		bookingService.checkForMaint(bookingRequest.getStartTime().toLocalTime(),
				bookingRequest.getEndTime().toLocalTime());
		// booking meeting room
		return new ResponseEntity<>(bookingService.bookMeetingRoom(bookingRequest), HttpStatus.CREATED);
	}

	@Operation(summary = "Cancel a Booked Meeting Room", description = "Cancels a previously booked meeting room based on the provided booking ID.", tags = {
			"Meeting Rooms" })
	@DeleteMapping("/cancel/{booking_id}")
	public ResponseEntity<Void> cancelMeetingRoomBooking(@PathVariable Long bookingId) {
		bookingService.deleteBooking(bookingId);
		return ResponseEntity.noContent().build();
	}

}
