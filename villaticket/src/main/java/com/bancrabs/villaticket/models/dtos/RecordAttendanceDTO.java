package com.bancrabs.villaticket.models.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RecordAttendanceDTO {
    @NotEmpty
    @org.hibernate.validator.constraints.UUID
    private UUID userId;

    @NotEmpty
    @org.hibernate.validator.constraints.UUID
    private UUID eventId;
}
