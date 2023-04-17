package com.booking.bookmyroom.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.booking.bookmyroom.data.model.ApiError;
import com.booking.bookmyroom.exceptions.BadRequestException;
import com.booking.bookmyroom.exceptions.BookingException;

/**
 * Global exception handler
 *
 */
@RestControllerAdvice
public class BookingExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BookingException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ResponseEntity<ApiError> handleBookingException(BookingException ex) {
		ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage());
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
	
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiError> handleBadRequestException(BadRequestException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ApiError> handleGenericException(Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
