package com.bancrabs.villaticket.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.Sponsor;

public interface SponsorRepository extends ListCrudRepository<Sponsor, String>{
    List<Sponsor> findAllByEventId(UUID eventId);
}
