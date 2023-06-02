package com.bancrabs.villaticket.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.TicketQR;

public interface TicketQRRepository extends ListCrudRepository<TicketQR, UUID>{
    
}
