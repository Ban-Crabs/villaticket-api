package com.bancrabs.villaticket.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bancrabs.villaticket.models.dtos.response.PageResponseDTO;
import com.bancrabs.villaticket.models.dtos.save.SaveEventDTO;
import com.bancrabs.villaticket.models.dtos.save.SaveGenericDTO;
import com.bancrabs.villaticket.models.entities.Event;
import com.bancrabs.villaticket.services.CategoryService;
import com.bancrabs.villaticket.services.EventService;
import com.bancrabs.villaticket.services.ImageService;
import com.bancrabs.villaticket.services.LocationService;
import com.bancrabs.villaticket.services.SponsorService;
import com.bancrabs.villaticket.services.TypeService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/event")
public class EventController {
    
    @Autowired
    private EventService eventService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/visible")
    public ResponseEntity<?> getAllVisible(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "amt", defaultValue = "10") int size){
        try{
            Page<Event> rawEvents = eventService.findAllVisibleEvents(page, size);
            PageResponseDTO<Event> response = new PageResponseDTO<>(rawEvents.getContent(), rawEvents.getTotalPages(), rawEvents.getTotalElements());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "amt", defaultValue = "10") int size){
        try{
            Page<Event> rawEvents = eventService.findAll(page, size);
            PageResponseDTO<Event> response = new PageResponseDTO<>(rawEvents.getContent(), rawEvents.getTotalPages(), rawEvents.getTotalElements());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@ModelAttribute @Valid SaveEventDTO data, BindingResult result){
        try{
            if(result.hasErrors()){
                return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
            }
            eventService.saveEvent(data);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        }
        catch(Exception e){
            switch(e.getMessage()){
                case "Type not found":
                    return new ResponseEntity<>("Type not found", HttpStatus.NOT_FOUND);
                case "Location not found":
                    return new ResponseEntity<>("Location not found", HttpStatus.NOT_FOUND);
                case "Event already exists":
                    return new ResponseEntity<>("Event already exists", HttpStatus.CONFLICT);
                default:
                    return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathParam("id") UUID id){
        try{
            return new ResponseEntity<>(eventService.findById(id), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(@PathParam("id") UUID id, @ModelAttribute @Valid SaveEventDTO data, BindingResult result){
        try{
            if(result.hasErrors()){
                return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
            }
            eventService.updateEvent(id, data);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }
        catch(Exception e){
            switch(e.getMessage()){
                case "Type not found":
                    return new ResponseEntity<>("Type not found", HttpStatus.NOT_FOUND);
                case "Location not found":
                    return new ResponseEntity<>("Location not found", HttpStatus.NOT_FOUND);
                case "Event not found":
                    return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
                default:
                    return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathParam("id") UUID id){
        try{
            eventService.deleteEvent(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/type")
    public ResponseEntity<?> getAllTypes(){
        try{
            return new ResponseEntity<>(typeService.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/type")
    public ResponseEntity<?> getEventType(@PathParam("id") UUID id){
        try{
            Event event = eventService.findById(id);
            return new ResponseEntity<>(event.getType(), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/location")
    public ResponseEntity<?> getAllLocations(){
        try{
            return new ResponseEntity<>(locationService.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/location")
    public ResponseEntity<?> getEventLocation(@PathParam("id") UUID id){
        try{
            Event event = eventService.findById(id);
            return new ResponseEntity<>(event.getLocation(), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sponsor")
    public ResponseEntity<?> getAllSponsors(){
        try{
            return new ResponseEntity<>(sponsorService.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/sponsor")
    public ResponseEntity<?> getEventSponsor(@PathParam("id") UUID id){
        try{
            return new ResponseEntity<>(sponsorService.findAllByEvent(id), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/sponsor")
    public ResponseEntity<?> addSponsor(@ModelAttribute @Valid SaveGenericDTO data, BindingResult result){
        try{
            if(result.hasErrors()){
                return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
            }
            sponsorService.save(data);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        }
        catch(Exception e){
            switch(e.getMessage()){
                case "Event not found":
                    return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
                default:
                    return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<?> getEventImage(@PathParam("id") UUID id){
        try{
            return new ResponseEntity<>(imageService.findAllByEvent(id), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/category")
    public ResponseEntity<?> getEventCategory(@PathParam("id") UUID id){
        try{
            return new ResponseEntity<>(categoryService.findAllByEvent(id), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
