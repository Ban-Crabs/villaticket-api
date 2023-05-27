package com.bancrabs.villaticket.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.Type;

public interface TypeRepository extends ListCrudRepository<Type, String>{
    Type findByNameOrId(String identifier);
}
