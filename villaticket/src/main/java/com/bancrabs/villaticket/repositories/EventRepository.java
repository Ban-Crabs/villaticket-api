package com.bancrabs.villaticket.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.Event;

public interface EventRepository extends ListCrudRepository<Event, UUID>{
    List<Event> findByVisibilityIsTrue();
    List<Event> findByVisibilityIsFalse();
    List<Event> findAllByOrderByDateAsc();
    List<Event> findAllByOrderByDateDesc();
    List<Event> findByEndTimeIsNotNull();
    List<Event> findByEndTimeIsNull();
    List<Event> findByTitle(String title);
    List<Event> findByTitleContainingIgnoreCase(String title);
    List<Event> findByTypeID(String typeID);
    List<Event> findByLocationID(String locationID);
}
