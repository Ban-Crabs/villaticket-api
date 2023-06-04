package com.bancrabs.villaticket.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.TicketQR;

public interface TicketQRRepository extends ListCrudRepository<TicketQR, UUID>{
    TicketQR findByTicketIdAndQrId(UUID ticketId, UUID qrId);
    List<TicketQR> findByTicketId(UUID ticketId);
}
