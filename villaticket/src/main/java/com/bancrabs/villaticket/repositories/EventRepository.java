package com.bancrabs.villaticket.repositories;

import java.sql.Timestamp;
import java.util.Date;
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
    List<Event> findByTitleContainingIgnoreCase(String title);
    List<Event> findByTypeId(String typeID);
    List<Event> findByLocationId(String locationID);
    Event findByTitleAndDateAndStartTime(String title, Date date, Timestamp startTime);
}
