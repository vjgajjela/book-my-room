package com.booking.bookmyroom.data.repo;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking.bookmyroom.data.model.MaintTiming;

/**
 * Repository to manage Maintenance Timings
 *
 */
public interface MaintTimingRepository extends JpaRepository<MaintTiming, Long> {

	@Query("SELECT mt FROM MaintTiming mt WHERE mt.startTime <= :endTime AND mt.endTime >= :startTime")
	List<MaintTiming> findMaintByTimeRange(LocalTime startTime, LocalTime endTime);
}
