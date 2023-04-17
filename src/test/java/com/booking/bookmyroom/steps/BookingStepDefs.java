package com.booking.bookmyroom.steps;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.booking.bookmyroom.data.model.ApiError;
import com.booking.bookmyroom.data.model.Booking;
import com.booking.bookmyroom.data.model.BookingRequest;
import com.booking.bookmyroom.data.model.MeetingRoom;
import com.booking.bookmyroom.utils.Constants;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.Gson;

public class BookingStepDefs {

	RestTemplate restTemplate = new RestTemplate();

	private String baseUrl;

	ResponseEntity<ApiError> errorResponse;

	String errorMessage;

	ResponseEntity<List<MeetingRoom>> meetingRooms;

	ResponseEntity<Booking> booking;

	@LocalServerPort
	private int port;

	@Given("User searching for meeting room")
	public void searching_rooms() {
		baseUrl = "http://localhost:" + port + "/api/v1/meeting-rooms?start_time={start_time}&end_time={end_time}";
	}

	@When("User provide input as {string} for {string} scenario")
	public void searching_rooms(String inputType, String scenario) {
		Map<String, String> params = TestUtils.getInputDates(inputType);
		try {
			if (scenario.equalsIgnoreCase("failure")) {
				errorResponse = restTemplate.getForEntity(baseUrl, ApiError.class, params);
			} else {
				meetingRooms = restTemplate.exchange(
						UriComponentsBuilder.fromUriString(baseUrl).buildAndExpand(params).toUri(), HttpMethod.GET,
						null, new ParameterizedTypeReference<List<MeetingRoom>>() {
						});
			}
		} catch (HttpClientErrorException.BadRequest ex) {
			ApiError apiError = new Gson().fromJson(ex.getResponseBodyAsString(), ApiError.class);
			apiError.setStatus(HttpStatus.valueOf(ex.getStatusCode().value()));
			errorResponse = new ResponseEntity<>(apiError, apiError.getStatus());
		} catch (HttpClientErrorException ex) {
			ApiError apiError = new Gson().fromJson(ex.getResponseBodyAsString(), ApiError.class);
			apiError.setStatus(HttpStatus.valueOf(ex.getStatusCode().value()));
			errorResponse = new ResponseEntity<>(apiError, apiError.getStatus());
		} catch (Exception ex) {
			errorMessage = ex.getMessage();
			throw ex;
		}
	}

	@Then("Verify the HTTP status code is {int} for {string} scenario")
	public void verify_http_status_code(int httpStatusCode, String scenario) {
		if (scenario.equalsIgnoreCase("failure")) {
			assertEquals(httpStatusCode, errorResponse.getStatusCode().value());
		} else {
			if(booking != null && booking.getStatusCode() != null) {
				assertEquals(httpStatusCode, booking.getStatusCode().value());
			}
			if(meetingRooms != null && meetingRooms.getStatusCode() != null) {
				assertEquals(httpStatusCode, meetingRooms.getStatusCode().value());
			}
		}
	}

	@And("Verify the status is {string}")
	public void verify_http_status(String status) {
		assertEquals(status, errorResponse.getBody().getStatus().toString());
	}

	@And("Error message is {string}")
	public void verify_error_message(String errorMessage) {
		assertEquals(errorMessage, errorResponse.getBody().getMessage());
	}

	@And("Verify the result has meeting rooms")
	public void verify_meeting_rooms() {
		assertTrue(!meetingRooms.getBody().isEmpty());
	}

	@And("Verify booking details with capacity {int}")
	public void verify_booking_details(int capacity) {
		assertNotNull(booking.getBody().getId());
		assertNotNull(booking.getBody().getMeetingRoom().getId());
		assertTrue(booking.getBody().getMeetingRoom().getCapacity() >= capacity);
	}

	@Given("User booking meeting room")
	public void booking_rooms() {
		baseUrl = "http://localhost:" + port + "/api/v1/meeting-rooms/book";
	}

	@When("User provide input as {string} and capacity {int} for {string} scenario with key {string}")
	public void booking_rooms(String inputType, int capacity, String scenario, String key) {

		BookingRequest bookingRequest = TestUtils.getBookingRequest(inputType);
		bookingRequest.setCapacity(capacity);
		RestTemplate restTemplate = new RestTemplate();

		// Set headers
		HttpHeaders headers = new HttpHeaders();
		headers.set(Constants.IDMP_KEY, key);

		// Set request entity
		HttpEntity<Object> request = new HttpEntity<>(bookingRequest, headers);
		try {
			if (scenario.equalsIgnoreCase("failure")) {
				errorResponse = restTemplate.exchange(baseUrl, HttpMethod.POST, request,
						new ParameterizedTypeReference<ApiError>() {
						});
			} else {
				booking = restTemplate.exchange(baseUrl, HttpMethod.POST, request,
						new ParameterizedTypeReference<Booking>() {
						});
			}

		} catch (HttpClientErrorException.BadRequest ex) {
			ApiError apiError = new Gson().fromJson(ex.getResponseBodyAsString(), ApiError.class);
			errorResponse = new ResponseEntity<>(apiError, apiError.getStatus());
		} catch (HttpClientErrorException ex) {
			ApiError apiError = new Gson().fromJson(ex.getResponseBodyAsString(), ApiError.class);
			errorResponse = new ResponseEntity<>(apiError, apiError.getStatus());
		} catch (Exception ex) {
			errorMessage = ex.getMessage();
			throw ex;
		}
	}
}
