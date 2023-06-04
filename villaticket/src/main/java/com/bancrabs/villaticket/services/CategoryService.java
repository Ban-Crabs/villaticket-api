package com.bancrabs.villaticket.services;

import java.util.List;
import java.util.UUID;

import com.bancrabs.villaticket.models.dtos.SaveGenericDTO;
import com.bancrabs.villaticket.models.entities.Category;

public interface CategoryService {
    Boolean save(SaveGenericDTO data) throws Exception;
    Boolean delete(SaveGenericDTO data) throws Exception;

    List<Category> findAll();
    Category findById(String id);
    List<Category> findAllByEvent(UUID eventId);
    List<Category> findByName(String name);
}
