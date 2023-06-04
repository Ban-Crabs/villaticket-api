package com.bancrabs.villaticket.models.dtos.save;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveLocationDTO {

    @NotEmpty
    @Pattern(regexp = "[A-Z]{3}[0-9]{6}")
    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    @org.hibernate.validator.constraints.UUID
    private UUID eventId;

    private Boolean isAvailable;

    public SaveLocationDTO(String id, String name, UUID eventId){
        this.id = id;
        this.name = name;
        this.eventId = eventId;
        this.isAvailable = true;
    }
}
