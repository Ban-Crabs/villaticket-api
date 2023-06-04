package com.bancrabs.villaticket.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDTO {
    
    @NotEmpty
    private String id;
    
    @NotEmpty
    private String password;
}
