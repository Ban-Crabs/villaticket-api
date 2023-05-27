package com.bancrabs.villaticket.services;

import com.bancrabs.villaticket.models.dtos.SaveEventDTO;
import com.bancrabs.villaticket.models.entities.Location;
import com.bancrabs.villaticket.models.entities.Type;

public interface EventService {
    void saveEvent(SaveEventDTO data, Type type, Location location);
}
