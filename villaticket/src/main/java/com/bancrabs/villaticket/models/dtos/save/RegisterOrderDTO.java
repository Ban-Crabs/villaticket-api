package com.bancrabs.villaticket.models.dtos.save;

import java.util.Date;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterOrderDTO {

    @NotEmpty
    private Date purchaseDate;
    
    @NotEmpty
    private String purchaseMethod;
    
    @NotEmpty
    private String userId;
}
