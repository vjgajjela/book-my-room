package com.booking.bookmyroom.data.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Api error pojo to be sent as response to users in case of any errors
 *
 */
@Data
@AllArgsConstructor
public class ApiError {

	private HttpStatus status;
	private String message;
}
