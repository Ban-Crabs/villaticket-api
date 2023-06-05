package com.bancrabs.villaticket.services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancrabs.villaticket.models.dtos.save.SaveEventDTO;
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
            Event toSave = eventRepository.findByTitleAndDateAndStartTime(data.getTitle(), data.getDate(), data.getStartTime());
            if(toSave == null){
                toSave = new Event(data.getTitle(), type, location, data.getDate(), data.getStartTime(), data.getEndTime(), data.getStatus(), data.getIsVisible());
            }
            else{
                throw new Exception("Event already exists");
            }
            toSave.setTitle(data.getTitle());
            toSave.setType(type);
            toSave.setLocation(location);
            toSave.setDate(data.getDate());
            toSave.setStartTime(data.getStartTime());
            toSave.setEndTime(data.getEndTime());
            toSave.setStatus(data.getStatus());
            toSave.setIsVisible(data.getIsVisible());
            eventRepository.save(toSave);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean updateEvent(UUID eventId, SaveEventDTO data, Type type, Location location) throws Exception {
        try{
            Event toUpdate = eventRepository.findById(eventId).orElse(null);
            if(toUpdate != null){
                toUpdate.setTitle(data.getTitle());
                toUpdate.setType(type);
                toUpdate.setLocation(location);
                toUpdate.setDate(data.getDate());
                toUpdate.setStartTime(data.getStartTime());
                toUpdate.setEndTime(data.getEndTime());
                toUpdate.setStatus(data.getStatus());
                toUpdate.setIsVisible(data.getIsVisible());
                eventRepository.save(toUpdate);
                return true;
            }
            else{
                throw new Exception("Event not found");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
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
        if(type == null){
            System.out.println("Type not found");
            return null;
        }
        return eventRepository.findByTypeId(type.getId());
    }

    @Override
    public List<Event> findAllEventsByLocation(String locationID) {
        Location location = locationService.findByIdOrName(locationID);
        if(location == null){
            System.out.println("Location not found");
            return null;
        }
        return eventRepository.findByLocationId(location.getId());
    }

    @Override
    public Event findById(UUID id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean deleteEvent(UUID eventId) throws Exception {
        try{
            Event toDelete = eventRepository.findById(eventId).orElse(null);
            if(toDelete != null){
                eventRepository.delete(toDelete);
                return true;
            }
            else{
                throw new Exception("Event not found");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean toggleVisibility(UUID eventId) throws Exception {
        try{
            Event toToggle = eventRepository.findById(eventId).orElse(null);
            if(toToggle != null){
                toToggle.setIsVisible(!toToggle.getIsVisible());
                eventRepository.save(toToggle);
                return true;
            }
            else{
                throw new Exception("Event not found");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
}
