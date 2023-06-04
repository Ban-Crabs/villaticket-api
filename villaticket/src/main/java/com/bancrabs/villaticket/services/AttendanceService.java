package com.bancrabs.villaticket.services;

import java.util.List;
import java.util.UUID;

import com.bancrabs.villaticket.models.dtos.RecordAttendanceDTO;
import com.bancrabs.villaticket.models.entities.Attendance;

public interface AttendanceService {
    Boolean save(RecordAttendanceDTO data) throws Exception;

    List<Attendance> findAll();
    List<Attendance> findByUserId(UUID userId);
    List<Attendance> findByEventId(UUID eventId);
}
