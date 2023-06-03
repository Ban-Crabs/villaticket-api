package com.bancrabs.villaticket.services;

import java.util.List;

import com.bancrabs.villaticket.models.dtos.SaveEventDTO;
import com.bancrabs.villaticket.models.entities.Event;
import com.bancrabs.villaticket.models.entities.Location;
import com.bancrabs.villaticket.models.entities.Type;

public interface EventService {
    Boolean saveEvent(SaveEventDTO data, Type type, Location location) throws Exception;

    List<Event> findAll();
    List<Event> findAllUpcomingEvents();
    List<Event> findAllPastEvents();
    List<Event> findAllEventsByType(String typeID);
    List<Event> findAllEventsByLocation(String locationID);
    List<Event> findAllVisibleEvents();
    List<Event> findAllInvisibleEvents();
}
