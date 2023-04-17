
-- Bookings
CREATE TABLE IF NOT EXISTS bookings (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  start_time TIMESTAMP NOT NULL,
  end_time TIMESTAMP NOT NULL,
  booked_time TIMESTAMP NOT NULL,
  booked_by VARCHAR(30),
  room_id BIGINT NOT NULL,
  FOREIGN KEY (room_id) REFERENCES meeting_rooms(id)
);
-- Maintenance Timings
CREATE TABLE IF NOT EXISTS maint_timings (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
   start_time TIME NOT NULL,
   end_time TIME NOT NULL
);
-- Meeting rooms
CREATE TABLE IF NOT EXISTS meeting_rooms (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  room_name VARCHAR(30) NOT NULL,
  capacity INT NOT NULL,
  UNIQUE (id, room_name, capacity)
);

DELETE FROM bookings;
DELETE FROM maint_timings;
DELETE FROM meeting_rooms;