package com.bancrabs.villaticket.controllers;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancrabs.villaticket.models.dtos.response.VerifyDTO;
import com.bancrabs.villaticket.models.dtos.save.CreateTicketDTO;
import com.bancrabs.villaticket.models.dtos.save.RegisterOrderDTO;
import com.bancrabs.villaticket.models.dtos.save.RegisterTicketDTO;
import com.bancrabs.villaticket.models.dtos.save.RegisterTicketQRDTO;
import com.bancrabs.villaticket.models.dtos.save.SaveTicketTransferDTO;
import com.bancrabs.villaticket.models.dtos.save.SaveTierDTO;
import com.bancrabs.villaticket.models.dtos.save.SaveTransferDTO;
import com.bancrabs.villaticket.models.dtos.save.VerifyTransferDTO;
import com.bancrabs.villaticket.services.OrderService;
import com.bancrabs.villaticket.services.TicketQRService;
import com.bancrabs.villaticket.services.TicketRegisterService;
import com.bancrabs.villaticket.services.TicketService;
import com.bancrabs.villaticket.services.TicketTransferService;
import com.bancrabs.villaticket.services.TierService;
import com.bancrabs.villaticket.services.TransferService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    
    @Autowired
    private TicketService ticketService;

    @Autowired
    private TierService tierService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private TransferService transferService;

    @Autowired
    private TicketTransferService ticketTransferService;

    @Autowired
    private TicketRegisterService ticketRegisterService;

    @Autowired
    private TicketQRService ticketQRService;

    @PostMapping("/")
    public ResponseEntity<?> createTicket(@ModelAttribute @Valid CreateTicketDTO data, BindingResult result){
        try{
            if(result.hasErrors()){
                return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
            }
            if(ticketService.save(data)){
                return new ResponseEntity<>("Created", HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>("Tier sold out", HttpStatus.CONFLICT);
            }
        }
        catch(Exception e){
            switch(e.getMessage()){
                case "Tier not found":
                    return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
                case "User not found":
                    return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
                default:
                    return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("/order/{id}")
    public ResponseEntity<?> orderTicket(@PathParam("id") UUID ticketId, @ModelAttribute @Valid RegisterOrderDTO data, BindingResult result){
        try{
            if(result.hasErrors()){
                return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
            }
            UUID newOrder = orderService.save(data);
            if(newOrder != null){
                ticketRegisterService.save(new RegisterTicketDTO(ticketId, newOrder));
                return new ResponseEntity<>("Created", HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>("Couldn't place order", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch(Exception e){
            switch(e.getMessage()){
                case "User not found":
                    return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
                default:
                    return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        try{
            return new ResponseEntity<>(ticketService.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathParam("id") UUID id){
        try{
            return new ResponseEntity<>(ticketService.findById(id), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateTicket(@PathParam("id") UUID id, @ModelAttribute @Valid CreateTicketDTO data, BindingResult result){
        try{
            if(result.hasErrors()){
                return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
            }
            ticketService.update(id, data);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }
        catch(Exception e){
            switch(e.getMessage()){
                case "Ticket not found":
                    return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
                case "Tier not found":
                    return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
                case "User not found":
                    return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
                case "Ticket already redeemed":
                    return new ResponseEntity<>(e, HttpStatus.CONFLICT);
                default:
                    return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTicket(@PathParam("id") UUID id){
        try{
            ticketService.delete(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        catch(Exception e){
            switch(e.getMessage()){
                case "Ticket not found":
                    return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
                default:
                    return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/tier")
    public ResponseEntity<?> getAllTiers(){
        try{
            return new ResponseEntity<>(tierService.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tier")
    public ResponseEntity<?> createTier(@ModelAttribute @Valid SaveTierDTO data, BindingResult result){
        try{
            if(result.hasErrors()){
                return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
            }
            tierService.save(data);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        }
        catch(Exception e){
            switch(e.getMessage()){
                case "Locale not found":
                    return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
                default:
                    return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferTicket(@ModelAttribute @Valid SaveTransferDTO data, BindingResult result){
        try{
            if(result.hasErrors()){
                return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
            }
            transferService.save(data);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        }
        catch(Exception e){
            switch(e.getMessage()){
                case "Ticket not found":
                    return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
                case "Sender not found":
                    return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
                case "Receiver not found":
                    return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
                case "Ticket already redeemed":
                    return new ResponseEntity<>(e, HttpStatus.CONFLICT);
                default:
                    return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("/transfer/{id}")
    public ResponseEntity<?> transferTicket(@PathParam("id") UUID ticketId, @ModelAttribute @Valid VerifyTransferDTO req, @ModelAttribute @Valid SaveTicketTransferDTO data, BindingResult result){
        try{
            if(result.hasErrors()){
                return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
            }
            VerifyDTO res = ticketTransferService.verify(req, data);
            if(res.getResult()){
                return new ResponseEntity<>("Verified", HttpStatus.OK);
            }
            else{
                switch(res.getMessage()){
                    case "Transfer already completed":
                        return new ResponseEntity<>(res.getMessage(), HttpStatus.CONFLICT);
                    case "Transfer reserved for another user":
                        return new ResponseEntity<>(res.getMessage(), HttpStatus.CONFLICT);
                    case "Transfer already attempted with this QR":
                        return new ResponseEntity<>(res.getMessage(), HttpStatus.CONFLICT);    
                    case "QR expired":
                        return new ResponseEntity<>(res.getMessage(), HttpStatus.BAD_REQUEST);
                    default:
                        return new ResponseEntity<>(res.getMessage(), HttpStatus.BAD_REQUEST);
                }
            }
        }
        catch(Exception e){
            switch(e.getMessage()){
                case "Ticket not found":
                    return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
                case "QR not found":
                    return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
                default:
                    return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("/redeem")
    public ResponseEntity<?> redeemTicket(@ModelAttribute @NotEmpty Timestamp timestamp, @ModelAttribute @Valid RegisterTicketQRDTO data, BindingResult result){
        try{
            if(result.hasErrors()){
                return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
            }
            VerifyDTO res = ticketQRService.verify(timestamp, data);
            if(res.getResult()){
                return new ResponseEntity<>("Redeemed", HttpStatus.OK);
            }
            else{
                switch(res.getMessage()){
                    case "Ticket already redeemed":
                        return new ResponseEntity<>(res.getMessage(), HttpStatus.CONFLICT);
                    case "Ticket redeem has already been attempted with this QR":
                        return new ResponseEntity<>(res.getMessage(), HttpStatus.CONFLICT);
                    case "QR expired":
                        return new ResponseEntity<>(res.getMessage(), HttpStatus.BAD_REQUEST);
                    default:
                        return new ResponseEntity<>(res.getMessage(), HttpStatus.BAD_REQUEST);
                }
            }
        }
        catch(Exception e){
            switch(e.getMessage()){
                case "Ticket not found":
                    return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
                case "QR not found":
                    return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
                default:
                    return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
