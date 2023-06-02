package com.bancrabs.villaticket.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.Locale;

public interface LocaleRepository extends ListCrudRepository<Locale, String>{
    
}
