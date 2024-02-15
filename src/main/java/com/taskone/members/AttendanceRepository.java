package com.taskone.members;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	Optional<Attendance> findByDateTime(String dateTime);
}
