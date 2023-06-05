package com.bancrabs.villaticket.models.dtos.save;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifyTransferDTO {
    
    @NotEmpty
    @org.hibernate.validator.constraints.UUID
    private UUID receiverId;

    @NotEmpty
    private Timestamp timestamp;
}
