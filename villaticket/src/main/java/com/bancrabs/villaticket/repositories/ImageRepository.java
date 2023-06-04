package com.bancrabs.villaticket.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.Image;

public interface ImageRepository extends ListCrudRepository<Image, String> {
    List<Image> findByEventId(UUID eventId);
}
