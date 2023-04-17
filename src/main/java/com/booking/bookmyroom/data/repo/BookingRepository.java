package com.booking.bookmyroom.data.repo;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.booking.bookmyroom.data.model.Booking;

/**
 * Repository to manage Bookings
 *
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query("SELECT COUNT(b) FROM Booking b WHERE b.meetingRoom.id = :roomId AND b.startTime <= :endTime AND b.endTime >= :startTime")
	Long countBookingsByMeetingRoomAndTimeRange(@Param("roomId") Long roomId,
			@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

}
