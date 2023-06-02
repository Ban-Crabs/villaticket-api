package com.bancrabs.villaticket.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.Sponsor;

public interface SponsorRepository extends ListCrudRepository<Sponsor, String>{
    
}
