package com.bancrabs.villaticket.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.TicketRegister;

public interface TicketRegisterRepository extends ListCrudRepository<TicketRegister, UUID>{
    
}
