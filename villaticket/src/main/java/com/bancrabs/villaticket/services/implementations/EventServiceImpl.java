package com.bancrabs.villaticket.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancrabs.villaticket.models.dtos.SaveEventDTO;
import com.bancrabs.villaticket.models.entities.Event;
import com.bancrabs.villaticket.models.entities.Location;
import com.bancrabs.villaticket.models.entities.Type;
import com.bancrabs.villaticket.repositories.EventRepository;
import com.bancrabs.villaticket.services.EventService;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void saveEvent(SaveEventDTO data, Type type, Location location) {
        Event toSave = new Event(data.getTitle(), type, location, data.getDate(), data.getStartTime(), data.getEndTime(), data.getStatus(), true);
        eventRepository.save(toSave);
    }
    
}
