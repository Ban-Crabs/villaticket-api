package com.bancrabs.villaticket.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.TicketTransfer;

public interface TicketTransferRepository extends ListCrudRepository<TicketTransfer, UUID>{
    TicketTransfer findByTransferIdAndQrId(UUID transferId, UUID qrId);
}
