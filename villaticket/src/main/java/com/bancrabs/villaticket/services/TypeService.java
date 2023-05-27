package com.bancrabs.villaticket.services;

import com.bancrabs.villaticket.models.dtos.SaveTypeDTO;
import com.bancrabs.villaticket.models.entities.Type;

public interface TypeService {
    void saveType(SaveTypeDTO data);
    Type findByNameOrId(String identifier);
}
