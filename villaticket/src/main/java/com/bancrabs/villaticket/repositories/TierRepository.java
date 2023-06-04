package com.bancrabs.villaticket.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.Tier;

public interface TierRepository extends ListCrudRepository<Tier, UUID>{
    Tier findByNameAndLocaleId(String name, String localeId);
    List<Tier> findByLocaleId(String localeId);
}
