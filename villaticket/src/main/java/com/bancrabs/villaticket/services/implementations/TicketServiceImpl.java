package com.bancrabs.villaticket.services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancrabs.villaticket.models.dtos.CreateTicketDTO;
import com.bancrabs.villaticket.models.entities.Ticket;
import com.bancrabs.villaticket.models.entities.Tier;
import com.bancrabs.villaticket.models.entities.User;
import com.bancrabs.villaticket.repositories.TicketRepository;
import com.bancrabs.villaticket.services.TicketService;
import com.bancrabs.villaticket.services.TierService;
import com.bancrabs.villaticket.services.UserService;

@Service
public class TicketServiceImpl implements TicketService{

    //TODO: Verify if the ticket has been exchanged before allowing changes

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TierService tierService;

    @Autowired
    private UserService userService;

    @Override
    public Boolean save(CreateTicketDTO data) throws Exception {
        try{
            Tier relatedTier = tierService.findById(data.getTierId());
            if(relatedTier == null){
                throw new Exception("Tier not found");
            }
            User relatedUser = userService.findById(data.getUserId());
            if(relatedUser == null){
                throw new Exception("User not found");
            }
            ticketRepository.save(new Ticket(relatedTier, relatedUser));
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean delete(UUID id) throws Exception {
        try{
            Ticket toDelete = ticketRepository.findById(id).orElse(null);
            if(toDelete == null){
                throw new Exception("Ticket not found");
            }
            ticketRepository.delete(toDelete);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findById(UUID id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public Ticket findByTierId(UUID tierId) {
        return ticketRepository.findByTierId(tierId);
    }

    @Override
    public Ticket findByUserId(UUID userId) {
        return ticketRepository.findByUserId(userId);
    }
    
}
