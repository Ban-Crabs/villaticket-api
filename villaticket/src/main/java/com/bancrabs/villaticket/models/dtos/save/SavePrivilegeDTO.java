package com.bancrabs.villaticket.models.dtos.save;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SavePrivilegeDTO {
    @NotEmpty
    private String name;

    @NotEmpty
    @org.hibernate.validator.constraints.UUID
    private UUID userId;
}
