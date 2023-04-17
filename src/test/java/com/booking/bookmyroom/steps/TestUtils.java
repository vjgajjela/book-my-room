package com.booking.bookmyroom.steps;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.booking.bookmyroom.data.model.BookingRequest;
import com.booking.bookmyroom.utils.Constants;

public class TestUtils {

	public static Map<String, String> getInputDates(String type) {

		Map<String, String> datesMap = new HashMap<String, String>();
		LocalDateTime start = LocalDateTime.now();
		LocalDateTime end = LocalDateTime.now();
		switch (type) {
		case "INVALID_TIME": {
			datesMap.put(Constants.START_TIME, "2023-04-16T21:3S");
			datesMap.put(Constants.END_TIME, "2023-04-16T21:3J");
			return datesMap;
		}
		case "PAST_DATES": {
			datesMap.put(Constants.START_TIME, convertTimeToString(start.minusSeconds(10)));
			datesMap.put(Constants.END_TIME, convertTimeToString(end.minusSeconds(10)));
			return datesMap;
		}
		case "INCORRECT_TIME_RANGE": {
			datesMap.put(Constants.START_TIME, convertTimeToString(start.plusHours(2)));
			datesMap.put(Constants.END_TIME, convertTimeToString(end.plusHours(1)));
			return datesMap;
		}
		case "NOT_15_MIN_TIME_RANGE": {
			datesMap.put(Constants.START_TIME,
					convertTimeToString(start.getMinute() % 15 == 0 ? start.plusMinutes(33) : start.plusMinutes(15)));
			datesMap.put(Constants.END_TIME,
					convertTimeToString(end.getMinute() % 15 == 0 ? end.plusMinutes(117) : end.plusMinutes(150)));
			return datesMap;
		}
		case "MAINT_TIME_INCL": {
			datesMap.put(Constants.START_TIME,
					LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth() + 1, 9, 0).toString());
			datesMap.put(Constants.END_TIME,
					LocalDateTime.of(end.getYear(), end.getMonth(), end.getDayOfMonth() + 1, 9, 15).toString());
			return datesMap;
		}
		case "MAINT_TIME_EXCL": {
			datesMap.put(Constants.START_TIME,
					LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth() + 1, 8, 0).toString());
			datesMap.put(Constants.END_TIME,
					LocalDateTime.of(end.getYear(), end.getMonth(), end.getDayOfMonth() + 1, 10, 0).toString());
			return datesMap;
		}
		case "MAINT_TIME_START_EXCL": {
			datesMap.put(Constants.START_TIME,
					LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth() + 1, 8, 0).toString());
			datesMap.put(Constants.END_TIME,
					LocalDateTime.of(end.getYear(), end.getMonth(), end.getDayOfMonth() + 1, 9, 15).toString());
			return datesMap;
		}
		case "MAINT_TIME_END_EXCL": {
			datesMap.put(Constants.START_TIME,
					LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth() + 1, 9, 0).toString());
			datesMap.put(Constants.END_TIME,
					LocalDateTime.of(end.getYear(), end.getMonth(), end.getDayOfMonth() + 1, 10, 0).toString());
			return datesMap;
		}
		case "FUTURE_TIME": {
			datesMap.put(Constants.START_TIME,
					LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth() + 1, 14, 0).toString());
			datesMap.put(Constants.END_TIME,
					LocalDateTime.of(end.getYear(), end.getMonth(), end.getDayOfMonth() + 1, 15, 0).toString());
			return datesMap;
		}

		case "CURRENT_TIME": {
			datesMap.put(Constants.START_TIME,
					LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth(), 22, 0).toString());
			datesMap.put(Constants.END_TIME,
					LocalDateTime.of(end.getYear(), end.getMonth(), end.getDayOfMonth(), 23, 00).toString());
			return datesMap;
		}

		default:
			return datesMap;
		}

	}

	public static String convertTimeToString(LocalDateTime time) {
		return LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), time.getHour(), time.getMinute())
				.toString();
	}

	public static BookingRequest getBookingRequest(String type) {
		BookingRequest bookingRequest = new BookingRequest();
		LocalDateTime start = LocalDateTime.now();
		LocalDateTime end = LocalDateTime.now();
		switch (type) {

		case "PAST_DATES": {
			bookingRequest.setStartTime(start.minusDays(2));
			bookingRequest.setEndTime(end.minusDays(2));
			break;
		}

		case "TODAYS_DATE_PAST_TIME": {
			bookingRequest.setStartTime(start.minusSeconds(10));
			bookingRequest.setEndTime(end.minusSeconds(10));
			break;
		}

		case "INCORRECT_TIME_RANGE": {
			bookingRequest.setStartTime(start.plusHours(2));
			bookingRequest.setEndTime(end.plusHours(1));
			break;
		}
		case "NOT_15_MIN_TIME_RANGE": {
			bookingRequest.setStartTime(start.getMinute() % 15 == 0 ? start.plusMinutes(33) : start.plusMinutes(15));
			bookingRequest.setEndTime(end.getMinute() % 15 == 0 ? end.plusMinutes(117) : end.plusMinutes(150));
			break;
		}
		case "MAINT_TIME_INCL": {
			bookingRequest
					.setStartTime(LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth(), 23, 30));
			bookingRequest.setEndTime(LocalDateTime.of(end.getYear(), end.getMonth(), end.getDayOfMonth(), 23, 45));
			break;
		}
		case "MAINT_TIME_EXCL": {
			bookingRequest
					.setStartTime(LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth(), 22, 0));
			bookingRequest.setEndTime(LocalDateTime.of(end.getYear(), end.getMonth(), end.getDayOfMonth(), 23, 45));
			break;
		}
		case "CURRENT_TIME": {
			bookingRequest
					.setStartTime(LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth(), 22, 0));
			bookingRequest.setEndTime(LocalDateTime.of(end.getYear(), end.getMonth(), end.getDayOfMonth(), 23, 00));
			break;
		}
		case "FUTURE_TIME": {
			bookingRequest.setStartTime(
					LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth() + 1, 23, 0));
			bookingRequest.setEndTime(LocalDateTime.of(end.getYear(), end.getMonth(), end.getDayOfMonth() + 1, 23, 30));
			break;
		}
		default:
			break;
		}
		return bookingRequest;
	}

}
