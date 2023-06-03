package com.bancrabs.villaticket.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancrabs.villaticket.models.dtos.SaveEventDTO;
import com.bancrabs.villaticket.models.entities.Event;
import com.bancrabs.villaticket.models.entities.Location;
import com.bancrabs.villaticket.models.entities.Type;
import com.bancrabs.villaticket.repositories.EventRepository;
import com.bancrabs.villaticket.services.EventService;
import com.bancrabs.villaticket.services.LocationService;
import com.bancrabs.villaticket.services.TypeService;

import jakarta.transaction.Transactional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TypeService typeService;

    @Autowired
    private LocationService locationService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean saveEvent(SaveEventDTO data, Type type, Location location) throws Exception{
        try{
            Event toSave = eventRepository.findByTitle(data.getTitle());
            if(toSave == null){
                toSave = new Event(data.getTitle(), type, location, data.getDate(), data.getStartTime(), data.getEndTime(), data.getStatus(), true);
            }else{
                toSave.setTitle(data.getTitle());
                toSave.setType(type);
                toSave.setLocation(location);
                toSave.setDate(data.getDate());
                toSave.setStartTime(data.getStartTime());
                toSave.setEndTime(data.getEndTime());
                toSave.setStatus(data.getStatus());
                toSave.setIsVisible(true);
            }
            eventRepository.save(toSave);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> findAllUpcomingEvents() {
        return eventRepository.findByEndTimeIsNull();
    }

    @Override
    public List<Event> findAllPastEvents() {
        return eventRepository.findByEndTimeIsNotNull();
    }

    @Override
    public List<Event> findAllVisibleEvents() {
        return eventRepository.findByVisibilityIsTrue();
    }

    @Override
    public List<Event> findAllInvisibleEvents() {
        return eventRepository.findByVisibilityIsFalse();
    }

    @Override
    public List<Event> findAllEventsByType(String typeID) {
        Type type = typeService.findByNameOrId(typeID);
        return eventRepository.findByTypeId(type.getId());
    }

    @Override
    public List<Event> findAllEventsByLocation(String locationID) {
        Location location = locationService.findByIdOrName(locationID);
        return eventRepository.findByLocationId(location.getId());
    }
    
}
