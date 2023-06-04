package com.bancrabs.villaticket.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import com.bancrabs.villaticket.models.dtos.SaveTicketTransferDTO;
import com.bancrabs.villaticket.models.entities.TicketTransfer;

public interface TicketTransferService {
    Boolean delete(UUID id) throws Exception;
    Boolean verify(Timestamp timestamp, SaveTicketTransferDTO data) throws Exception;

    List<TicketTransfer> findAll();
    TicketTransfer findById(UUID id);
}
