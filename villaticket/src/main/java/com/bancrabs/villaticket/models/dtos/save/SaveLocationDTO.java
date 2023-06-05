package com.bancrabs.villaticket.models.dtos.save;

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
    private Boolean isAvailable;

    public SaveLocationDTO(String id, String name){
        this.id = id;
        this.name = name;
        this.isAvailable = true;
    }
}
