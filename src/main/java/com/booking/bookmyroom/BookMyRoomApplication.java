package com.booking.bookmyroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Application for Booking meeting rooms
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Meeting Rooms", version = "1.0.0", description = "This API is used for Booking meeting rooms", contact = @Contact(name = "Vijay Gajjelli", email = "vijaygajjelli@gmail.com")), tags = {
		@Tag(name = "Meeting Rooms", description = "APIs related to Meeting Rooms") })
public class BookMyRoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMyRoomApplication.class, args);
	}
}
