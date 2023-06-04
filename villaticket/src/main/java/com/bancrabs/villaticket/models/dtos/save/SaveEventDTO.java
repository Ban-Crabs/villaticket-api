package com.bancrabs.villaticket.models.dtos.save;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class SaveEventDTO {
    private String title;
    private String typeID;
    private String locationID;
    private Date date;
    private Timestamp startTime;
    @Nullable
    private Timestamp endTime;
    private String status;
    private Boolean isVisible;
}
