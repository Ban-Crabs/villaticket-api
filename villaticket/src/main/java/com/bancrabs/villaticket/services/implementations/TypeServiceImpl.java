package com.bancrabs.villaticket.services.implementations;

import org.springframework.stereotype.Service;

import com.bancrabs.villaticket.models.dtos.SaveTypeDTO;
import com.bancrabs.villaticket.models.entities.Type;
import com.bancrabs.villaticket.services.TypeService;

@Service
public class TypeServiceImpl implements TypeService{

    @Override
    public Boolean saveType(SaveTypeDTO data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveType'");
    }

    @Override
    public Type findByNameOrId(String identifier) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNameOrId'");
    }
    
}
