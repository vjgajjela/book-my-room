package com.booking.bookmyroom.exceptions;

/**
 * Custom exception for all bad api requests
 *
 */
public class BadRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1289647373164256764L;

	public BadRequestException(String message) {
		super(message);
	}

}
