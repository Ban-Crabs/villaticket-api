package com.bancrabs.villaticket.services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.bancrabs.villaticket.models.dtos.SaveGenericDTO;
import com.bancrabs.villaticket.models.entities.Category;
import com.bancrabs.villaticket.models.entities.Event;
import com.bancrabs.villaticket.repositories.CategoryRepository;
import com.bancrabs.villaticket.services.CategoryService;
import com.bancrabs.villaticket.services.EventService;

import jakarta.transaction.Transactional;

public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EventService eventService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean save(SaveGenericDTO data) throws Exception {
        try{
            Category check = categoryRepository.findById(data.getCode()).orElse(null);
            Event related = eventService.findById(data.getEventId());
            if(related == null){
                throw new Exception("Event not found");
            }
            if(check == null){
                categoryRepository.save(new Category(data.getCode(), data.getName(), related));
            }
            else{
                check.setId(data.getCode());
                check.setName(data.getName());
                check.setEvent(related);
                categoryRepository.save(check);
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
    public Boolean delete(SaveGenericDTO data) throws Exception {
        try{
            Category check = categoryRepository.findById(data.getCode()).orElse(null);
            if(check != null){
                categoryRepository.delete(check);
                return true;
            }
            else{
                throw new Exception("Category not found");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(String id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> findAllByEvent(UUID eventId) {
        return categoryRepository.findByEventId(eventId);
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
    
}
