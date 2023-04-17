package com.booking.bookmyroom.data.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.booking.bookmyroom.data.model.MeetingRoom;

/**
 * Repository to manage Meeting rooms
 *
 */
@Repository
public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long> {

	@Query("SELECT m FROM MeetingRoom m WHERE NOT EXISTS (SELECT b FROM Booking b WHERE b.meetingRoom.id = m.id AND b.startTime <= :endTime AND b.endTime >= :startTime)")
	List<MeetingRoom> findAvailableMeetingRoomsByTimeRange(@Param("startTime") LocalDateTime startTime,
			@Param("endTime") LocalDateTime endTime);

    @Query("SELECT m FROM MeetingRoom m WHERE NOT EXISTS (SELECT b FROM Booking b WHERE b.meetingRoom = m AND b.startTime <= :endTime AND b.endTime >= :startTime) AND m.capacity >= :capacity ORDER BY m.capacity")
    List<MeetingRoom> findAvailableMeetingRoomsByTimeRangeAndCapacity(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime, @Param("capacity") int capacity);


}