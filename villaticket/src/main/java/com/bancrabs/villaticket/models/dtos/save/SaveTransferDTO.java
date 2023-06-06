package com.bancrabs.villaticket.models.dtos.save;

import java.util.UUID;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveTransferDTO {

    @NotEmpty
    @org.hibernate.validator.constraints.UUID
    private UUID senderId;
    
    @Nullable
    @org.hibernate.validator.constraints.UUID
    private UUID receiverId;

    @NotEmpty
    @org.hibernate.validator.constraints.UUID
    private UUID ticketId;
}
