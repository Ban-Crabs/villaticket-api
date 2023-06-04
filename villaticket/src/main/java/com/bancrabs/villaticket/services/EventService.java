package com.bancrabs.villaticket.services;

import java.util.List;
import java.util.UUID;

import com.bancrabs.villaticket.models.dtos.save.SaveEventDTO;
import com.bancrabs.villaticket.models.entities.Event;
import com.bancrabs.villaticket.models.entities.Location;
import com.bancrabs.villaticket.models.entities.Type;

public interface EventService {
    Boolean saveEvent(SaveEventDTO data, Type type, Location location) throws Exception;
    Boolean updateEvent(UUID eventId, SaveEventDTO data, Type type, Location location) throws Exception;
    Boolean deleteEvent(UUID eventId) throws Exception;
    Boolean toggleVisibility(UUID eventId) throws Exception;

    Event findById(UUID id);
    List<Event> findAll();
    List<Event> findAllUpcomingEvents();
    List<Event> findAllPastEvents();
    List<Event> findAllEventsByType(String typeID);
    List<Event> findAllEventsByLocation(String locationID);
    List<Event> findAllVisibleEvents();
    List<Event> findAllInvisibleEvents();
}
