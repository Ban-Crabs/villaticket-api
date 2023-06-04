package com.bancrabs.villaticket.models.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterTicketQRDTO {

    @NotEmpty
    @org.hibernate.validator.constraints.UUID
    private UUID ticketId;

    @NotEmpty
    @org.hibernate.validator.constraints.UUID
    private UUID qrId;
}
