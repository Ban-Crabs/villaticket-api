package com.bancrabs.villaticket.models.dtos.save;

import java.util.Date;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterOrderDTO {

    @NotEmpty
    private Date purchaseDate;
    
    @NotEmpty
    private String purchaseMethod;
    
    @NotEmpty
    @org.hibernate.validator.constraints.UUID
    private UUID userId;
}
