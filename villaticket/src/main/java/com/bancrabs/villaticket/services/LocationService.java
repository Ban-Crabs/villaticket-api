package com.bancrabs.villaticket.services;

import com.bancrabs.villaticket.models.dtos.SaveLocationDTO;
import com.bancrabs.villaticket.models.entities.Location;

public interface LocationService {
    Boolean saveLocation(SaveLocationDTO data) throws Exception;
    Boolean deleteLocation(String id) throws Exception;
    Boolean setAvailable(String id) throws Exception;
    Boolean setUnavailable(String id) throws Exception;

    Location findByIdOrName(String id);
}
