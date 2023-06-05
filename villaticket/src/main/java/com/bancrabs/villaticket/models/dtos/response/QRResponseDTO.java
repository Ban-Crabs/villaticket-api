package com.bancrabs.villaticket.models.dtos.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QRResponseDTO {
    private UUID relatedId;
    private UUID qrId;
}
