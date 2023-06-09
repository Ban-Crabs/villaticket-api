package com.bancrabs.villaticket.services;

import java.util.List;
import java.util.UUID;

import com.bancrabs.villaticket.models.dtos.save.CreateTicketDTO;
import com.bancrabs.villaticket.models.entities.Ticket;

public interface TicketService {
    Boolean save(CreateTicketDTO data) throws Exception;
    Boolean save(Ticket ticket);
    Boolean delete(UUID id) throws Exception;
    Boolean update(UUID id, CreateTicketDTO data) throws Exception;

    List<Ticket> findAll();
    Ticket findById(UUID id);
    List<Ticket> findByTierId(UUID tierId);
    Ticket findByUserId(UUID userId);
}
