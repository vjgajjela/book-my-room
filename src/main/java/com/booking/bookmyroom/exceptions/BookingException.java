package com.booking.bookmyroom.exceptions;

/**
 * Custom exception for all booking related exceptions
 *
 */
public class BookingException extends Exception {

	private static final long serialVersionUID = -5553438743308155431L;

	public BookingException(String message) {
		super(message);
	}
}
