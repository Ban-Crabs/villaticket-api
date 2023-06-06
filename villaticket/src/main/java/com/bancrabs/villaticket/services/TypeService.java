package com.bancrabs.villaticket.services;

import java.util.List;

import com.bancrabs.villaticket.models.dtos.save.SaveTypeDTO;
import com.bancrabs.villaticket.models.entities.Type;

public interface TypeService {
    Boolean save(SaveTypeDTO data) throws Exception;
    Boolean delete(String identifier) throws Exception;
    
    List<Type> findAll();
    Type findByNameOrId(String identifier);
}
