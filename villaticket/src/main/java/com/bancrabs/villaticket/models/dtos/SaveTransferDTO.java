package com.bancrabs.villaticket.models.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveTransferDTO {

    @NotEmpty
    @org.hibernate.validator.constraints.UUID
    private UUID senderId;
    
    @NotEmpty
    @org.hibernate.validator.constraints.UUID
    private UUID receiverId;
}
