package com.bancrabs.villaticket.models.dtos.save;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SaveTierDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    private Float price;
    
    @NotEmpty
    private Integer quantity;
    
    @NotEmpty
    @Pattern(regexp = "[A-Z]{3}[0-9]{3}")
    private String localeId;
}
