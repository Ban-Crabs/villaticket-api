package com.bancrabs.villaticket.models.dtos.save;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SaveLocaleDTO {
    
    @NotEmpty
    @Pattern(regexp = "[A-Z]{3}[0-9]{3}")
    private String id;

    @NotEmpty
    private String name;
}
