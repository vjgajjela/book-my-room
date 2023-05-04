package com.booking.bookmyroom.data.model;

import java.time.LocalTime;

import com.booking.bookmyroom.utils.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for Maintenance timings
 *
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "maint_timings")
@AllArgsConstructor
public class MaintTiming {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maint_seq")
    @SequenceGenerator(name = "maint_seq", sequenceName = "MAINT_SEQ", allocationSize = 1)
	private long id;
	@Column(name = Constants.START_TIME, nullable = false, columnDefinition = "TIME(0)")
	@Temporal(TemporalType.TIME)
	private LocalTime startTime;
	@Column(name = Constants.END_TIME, nullable = false, columnDefinition = "TIME(0)")
	@Temporal(TemporalType.TIME)
	private LocalTime endTime;

}
