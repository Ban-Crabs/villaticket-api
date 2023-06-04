package com.bancrabs.villaticket.services.implementations;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancrabs.villaticket.models.dtos.RegisterTicketQRDTO;
import com.bancrabs.villaticket.models.entities.QR;
import com.bancrabs.villaticket.models.entities.Ticket;
import com.bancrabs.villaticket.models.entities.TicketQR;
import com.bancrabs.villaticket.repositories.TicketQRRepository;
import com.bancrabs.villaticket.services.QRService;
import com.bancrabs.villaticket.services.TicketQRService;
import com.bancrabs.villaticket.services.TicketService;
import jakarta.transaction.Transactional;

@Service
public class TicketQRServiceImpl implements TicketQRService{

    @Autowired
    private TicketQRRepository ticketQRRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private QRService qrService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean verify(Timestamp timestamp, RegisterTicketQRDTO data) throws Exception {
        try{
            Ticket ticket = ticketService.findById(data.getTicketId());
            if(ticket == null){
                throw new Exception("Ticket not found");
            }
            QR qr = qrService.findById(data.getQrId());
            if(qr == null){
                throw new Exception("QR not found");
            }
            TicketQR check = ticketQRRepository.findByTicketIdAndQrId(data.getTicketId(), data.getQrId());
            if(check != null){
                throw new Exception("TicketQR already exists");
            }
            Integer toVerify = (int) qr.getCreationTime().getTime();
            Integer toCompare = (int) timestamp.getTime();
            if(toCompare - toVerify > 600000 || toCompare - toVerify < 0){
                throw new Exception("QR expired");
            }
            ticket.setResult(true);
            ticketService.save(ticket);
            ticketQRRepository.save(new TicketQR(timestamp, ticket, qr));
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean delete(UUID id) throws Exception {
        try{
            TicketQR check = ticketQRRepository.findById(id).orElse(null);
            if(check == null){
                throw new Exception("TicketQR not found");
            }
            ticketQRRepository.delete(check);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<TicketQR> findAll() {
        return ticketQRRepository.findAll();
    }

    @Override
    public List<TicketQR> findByTicketId(UUID ticketId) {
        return ticketQRRepository.findByTicketId(ticketId);
    }
    
}
