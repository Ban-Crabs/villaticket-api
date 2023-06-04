package com.bancrabs.villaticket.models.dtos;

import java.util.UUID;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveImageDTO {

    @NotEmpty
    @Size(min = 3, max = 20)
    private String id;

    @NotEmpty
    @URL
    private String url;

    @NotEmpty
    @org.hibernate.validator.constraints.UUID
    private UUID eventId;
}
