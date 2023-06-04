package com.bancrabs.villaticket.services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.bancrabs.villaticket.models.dtos.SaveImageDTO;
import com.bancrabs.villaticket.models.entities.Event;
import com.bancrabs.villaticket.models.entities.Image;
import com.bancrabs.villaticket.repositories.ImageRepository;
import com.bancrabs.villaticket.services.EventService;
import com.bancrabs.villaticket.services.ImageService;

import jakarta.transaction.Transactional;

public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private EventService eventService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean save(SaveImageDTO data) throws Exception {
        try{
            Image check = imageRepository.findById(data.getId()).orElse(null);
            Event related = eventService.findById(data.getEventId());
            if(related == null){
                throw new Exception("Event not found");
            }

            if(check == null){
                imageRepository.save(new Image(data.getId(), data.getUrl(), related));
            }
            else{
                check.setId(data.getId());
                check.setUrl(data.getUrl());
                check.setEvent(related);
                imageRepository.save(check);
            }
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean delete(SaveImageDTO data) throws Exception {
        try{
            Image check = imageRepository.findById(data.getId()).orElse(null);
            if(check != null){
                imageRepository.delete(check);
                return true;
            }
            else{
                throw new Exception("Image not found");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image findById(String id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public List<Image> findAllByEvent(UUID eventId) {
        return imageRepository.findByEventId(eventId);
    }
    
}
