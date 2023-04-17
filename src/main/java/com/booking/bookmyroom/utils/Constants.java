package com.booking.bookmyroom.utils;

public class Constants {

	public static final int TIME_INTERVAL = 15;
	// yyy-MM-dd'T'HH:mm:ss.SSS
	public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm";

	// Error messages

	public static final String ERR_INVALID_DATE_BOOKING = "Meetings can only be booked for today's date. Please ensure that the provided date is today's date.";

	public static final String ERR_PAST_TIME = "The provided date or time range is in the past and cannot be accepted for booking.";

	public static final String ERR_TIME_RANGE = "The end time should be greater than the start time.";

	public static final String ERR_TIME_RANGE_INCR = "The time range must be in increments of 15 minutes.";

	public static final String ERR_MAINT = "Meeting rooms not available at this time due to scheduled maintenance.";

	public static final String ERR_NO_ROOMS = "No meeting rooms are available at this time, please try again later.";

	public static final String ERR_BOOKING_FAILED = "Failed to lock meeting room for booking";

	public static final String ERR_CAPACITY = "Meeting rooms can only be booked if the capacity to do so is greater than one.";
	
	public static final String DUP_BOOKING = "Specified booking already exists";
	
	public static final String START_TIME = "start_time";
	
	public static final String END_TIME = "end_time";
	
	public static final String IDMP_KEY = "Idempotency-Key";
	
	public static final String DEFAULT_USER = "SYSTEM";
	
}
