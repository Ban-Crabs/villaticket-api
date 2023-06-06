package com.bancrabs.villaticket.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.Ticket;

public interface TicketRepository extends ListCrudRepository<Ticket, UUID>{
    List<Ticket> findByTierId(UUID tierId);
    Ticket findByUserId(UUID userId);
}
