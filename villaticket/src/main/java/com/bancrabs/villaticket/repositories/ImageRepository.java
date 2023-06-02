package com.bancrabs.villaticket.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.Image;

public interface ImageRepository extends ListCrudRepository<Image, String> {
    
}
