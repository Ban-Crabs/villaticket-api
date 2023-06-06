package com.bancrabs.villaticket.models.dtos.save;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaveTicketTransferDTO {

    @NotEmpty
    @org.hibernate.validator.constraints.UUID
    private UUID qrId;

    @NotEmpty
    @org.hibernate.validator.constraints.UUID
    private UUID transferId;

    public SaveTicketTransferDTO(UUID qrId, UUID transferId){
        this.qrId = qrId;
        this.transferId = transferId;
    }
}
