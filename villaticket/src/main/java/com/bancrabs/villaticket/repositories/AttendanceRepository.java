package com.bancrabs.villaticket.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bancrabs.villaticket.models.entities.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, UUID>{
    List<Attendance> findByUserId(UUID userId);
    List<Attendance> findByEventId(UUID eventId);
}
