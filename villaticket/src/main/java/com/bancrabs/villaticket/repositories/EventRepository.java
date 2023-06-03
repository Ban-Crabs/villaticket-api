package com.bancrabs.villaticket.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.Event;

public interface EventRepository extends ListCrudRepository<Event, UUID>{
    List<Event> findByVisibilityIsTrue();
    List<Event> findByVisibilityIsFalse();
    List<Event> findAllOrderByDateAsc();
    List<Event> findAllOrderByDateDesc();
    List<Event> findByEndTimeIsNotNull();
    List<Event> findByEndTimeIsNull();
    Event findByTitle(String title);
    List<Event> findByTitleContainingIgnoreCase(String title);
    List<Event> findByTypeId(String typeID);
    List<Event> findByLocationId(String locationID);
}
