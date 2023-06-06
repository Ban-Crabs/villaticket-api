package com.bancrabs.villaticket.models.dtos.save;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveEventDTO {

    @NotEmpty
    private String title;
    
    @NotEmpty
    private String typeID;
    
    @NotEmpty
    private String locationID;

    @NotEmpty
    private Date date;

    @NotEmpty
    private Timestamp startTime;

    @Nullable
    private Timestamp endTime;
    
    @NotEmpty
    private String status;

    @NotEmpty
    private Boolean isVisible;
}
