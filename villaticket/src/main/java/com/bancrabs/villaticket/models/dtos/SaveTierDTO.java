package com.bancrabs.villaticket.models.dtos;

import lombok.Data;

@Data
public class SaveTierDTO {
    private String name;
    private Float price;
    private Integer quantity;
    private String localeId;
}
