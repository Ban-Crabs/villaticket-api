package com.bancrabs.villaticket.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.Attendance;

public interface AttendanceRepository extends ListCrudRepository<Attendance, UUID>{
    List<Attendance> findByUserId(UUID userId);
    List<Attendance> findByEventId(UUID eventId);
}
