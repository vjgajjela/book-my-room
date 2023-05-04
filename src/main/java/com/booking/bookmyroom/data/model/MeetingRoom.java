package com.booking.bookmyroom.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for Meeting rooms
 *
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "meeting_rooms")
public class MeetingRoom {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meetings_seq")
    @SequenceGenerator(name = "meetings_seq", sequenceName = "MEETINGS_SEQ", allocationSize = 1)
	private long id;
	@Column(name = "room_name", nullable = false)
	private String name;
	@Column(name = "capacity", nullable = false)
	private int capacity;
	
	

}
	