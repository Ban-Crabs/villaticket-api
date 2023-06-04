package com.bancrabs.villaticket.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.Category;

public interface CategoryRepository extends ListCrudRepository<Category, String>{
    List<Category> findByName(String name);
    List<Category> findByEventId(UUID eventId);
    
}
