package com.booking.bookmyroom.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.booking.bookmyroom.data.model.BookingRequest;
import com.booking.bookmyroom.exceptions.BadRequestException;
import com.booking.bookmyroom.utils.Constants;

/**
 * Validation service
 *
 */
@Service
public class ValidationService {

	private Logger log = LoggerFactory.getLogger(ValidationService.class);

	/**
	 * To validate booking request
	 * 
	 * @param bookingRequest
	 * @throws BadRequestException
	 */
	public void validateBooking(BookingRequest bookingRequest) throws BadRequestException {
		LocalDateTime startTime = bookingRequest.getStartTime();
		LocalDateTime endTime = bookingRequest.getEndTime();

		// checking for todays date
		if (!startTime.toLocalDate().equals(LocalDateTime.now().toLocalDate())
				|| !endTime.toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
			log.debug(Constants.ERR_INVALID_DATE_BOOKING);
			throw new BadRequestException(Constants.ERR_INVALID_DATE_BOOKING);
		}

		this.validateDateAndTime(startTime, endTime);

		// checking capacity
		if (bookingRequest.getCapacity() <= 0) {
			log.debug(Constants.ERR_CAPACITY);
			throw new BadRequestException(Constants.ERR_CAPACITY);
		}
	}

	/**
	 * This method validates start time and end time for past dates, time range and
	 * interval
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws BadRequestException
	 */
	public void validateDateAndTime(LocalDateTime startTime, LocalDateTime endTime) throws BadRequestException {
		// checking for past dates
		if (startTime.compareTo(LocalDateTime.now()) <= 0 || endTime.compareTo(LocalDateTime.now()) <= 0) {
			log.debug(Constants.ERR_PAST_TIME);
			throw new BadRequestException(Constants.ERR_PAST_TIME);
		}

		// checking for valid time range
		if (startTime.compareTo(endTime) >= 0) {
			log.debug(Constants.ERR_TIME_RANGE);
			throw new BadRequestException(Constants.ERR_TIME_RANGE);
		}

		// checking for valid time interval
		if (startTime.getMinute() % Constants.TIME_INTERVAL != 0
				|| endTime.getMinute() % Constants.TIME_INTERVAL != 0) {
			log.debug(Constants.ERR_TIME_RANGE_INCR);
			throw new BadRequestException(Constants.ERR_TIME_RANGE_INCR);
		}
	}

}
