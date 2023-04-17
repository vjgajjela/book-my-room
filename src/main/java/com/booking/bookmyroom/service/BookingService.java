package com.booking.bookmyroom.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.bookmyroom.data.model.Booking;
import com.booking.bookmyroom.data.model.BookingRequest;
import com.booking.bookmyroom.data.model.MeetingRoom;
import com.booking.bookmyroom.data.repo.BookingRepository;
import com.booking.bookmyroom.data.repo.MaintTimingRepository;
import com.booking.bookmyroom.data.repo.MeetingRoomRepository;
import com.booking.bookmyroom.exceptions.BadRequestException;
import com.booking.bookmyroom.exceptions.BookingException;
import com.booking.bookmyroom.utils.BookingCache;
import com.booking.bookmyroom.utils.Constants;

import jakarta.persistence.LockTimeoutException;
import jakarta.transaction.Transactional;

/**
 * Booking service
 *
 */
@Service
public class BookingService {

	private Logger log = LoggerFactory.getLogger(BookingService.class);

	@Autowired
	private MaintTimingRepository maintTimingRepository;

	@Autowired
	private MeetingRoomRepository meetingRoomRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookingCache bookingCache;

	/**
	 * To check if the given schedule falls within maintenance timings
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws BookingException
	 */
	public void checkForMaint(LocalTime startTime, LocalTime endTime) throws BookingException {
		if (!maintTimingRepository.findMaintByTimeRange(startTime, endTime).isEmpty()) {
			log.debug(Constants.ERR_MAINT);
			throw new BookingException(Constants.ERR_MAINT);
		}
	}

	/**
	 * To check the request idempotency
	 * 
	 * @param idempotencyKey
	 * @throws BookingException
	 */
	public void checkIdempotency(String idempotencyKey) throws BookingException {
		if (bookingCache.checkIfKeyExistsInCache(idempotencyKey)) {
			throw new BookingException(Constants.DUP_BOOKING);
		} else {
			bookingCache.addKeyToCache(idempotencyKey);
		}
	}

	/**
	 * To booking meeting rooms. This method has transaction boundaries to avoid
	 * conflicts in booking
	 * 
	 * @param bookingRequest
	 * @return
	 * @throws BadRequestException
	 * @throws BookingException
	 */
	@Transactional
	public Booking bookMeetingRoom(BookingRequest bookingRequest) throws BadRequestException, BookingException {
		try {
			List<MeetingRoom> availableMeetingRooms = meetingRoomRepository
					.findAvailableMeetingRoomsByTimeRangeAndCapacity(bookingRequest.getStartTime(),
							bookingRequest.getEndTime(), bookingRequest.getCapacity());
			if (!availableMeetingRooms.isEmpty()) {
				MeetingRoom meetingRoom = availableMeetingRooms.get(0);
				Booking booking = new Booking();
				booking.setMeetingRoom(meetingRoom);
				booking.setStartTime(bookingRequest.getStartTime());
				booking.setEndTime(bookingRequest.getEndTime());
				booking.setBookingTime(LocalDateTime.now());
				booking.setBookedBy(Constants.DEFAULT_USER);
				return bookingRepository.save(booking);
			} else {
				log.debug(Constants.ERR_NO_ROOMS);
				throw new BookingException(Constants.ERR_NO_ROOMS);
			}
		} catch (LockTimeoutException ex) {
			throw new RuntimeException(Constants.ERR_BOOKING_FAILED);
		}
	}

	/**
	 * To Fetch available meeting rooms
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws BookingException
	 */
	public List<MeetingRoom> getAvailableMeetingRooms(LocalDateTime startTime, LocalDateTime endTime)
			throws BookingException {
		List<MeetingRoom> availableMeetingRooms = meetingRoomRepository.findAvailableMeetingRoomsByTimeRange(startTime,
				endTime);
		if (availableMeetingRooms.isEmpty()) {
			throw new BookingException(Constants.ERR_NO_ROOMS);
		}
		return availableMeetingRooms;
	}

	/**
	 * To delete bookings
	 * @param id
	 */
	public void deleteBooking(Long id) {
		bookingRepository.deleteById(id);
	}

}
