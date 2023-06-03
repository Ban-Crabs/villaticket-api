package com.bancrabs.villaticket.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.Location;

public interface LocationRepository extends ListCrudRepository<Location, String>{
    Location findByIdOrName(String id, String name);
}
