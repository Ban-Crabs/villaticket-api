package com.bancrabs.villaticket.services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancrabs.villaticket.models.dtos.save.SaveTicketTransferDTO;
import com.bancrabs.villaticket.models.dtos.save.VerifyTransferDTO;
import com.bancrabs.villaticket.models.entities.QR;
import com.bancrabs.villaticket.models.entities.TicketTransfer;
import com.bancrabs.villaticket.models.entities.Transfer;
import com.bancrabs.villaticket.repositories.TicketTransferRepository;
import com.bancrabs.villaticket.services.QRService;
import com.bancrabs.villaticket.services.TicketTransferService;
import com.bancrabs.villaticket.services.TransferService;
import com.bancrabs.villaticket.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class TicketTransferServiceImpl implements TicketTransferService {

    @Autowired
    private TicketTransferRepository ticketTransferRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TransferService transferService;

    @Autowired
    private QRService qrService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean verify(VerifyTransferDTO req, SaveTicketTransferDTO data) throws Exception {
        try {
            Transfer transfer = transferService.findById(data.getTransferId());
            if (transfer == null) {
                throw new Exception("Transfer not found");
            }
            QR qr = qrService.findById(data.getQrId());
            if (qr == null) {
                throw new Exception("QR not found");
            }
            TicketTransfer check = ticketTransferRepository.findByTransferIdAndQrId(data.getTransferId(), data.getQrId());
            if (check != null) {
                if(check.getTransfer().getReceiver() != null && check.getTransferTime() != null){
                    throw new Exception("Ticket already transferred");
                }
                else if(check.getTransfer().getReceiver() != null && check.getTransferTime() == null){
                    if(check.getTransfer().getReceiver().getId() == req.getReceiverId()){
                        Integer toVerify = (int) qr.getCreationTime().getTime();
                        Integer toCompare = (int) req.getTimestamp().getTime();
                        if (toCompare - toVerify > 600000 || toCompare - toVerify < 0) {
                            transfer.setResult(false);
                            transferService.save(transfer);
                            return false;
                        }
                        else{
                            transfer.setResult(true);
                            transferService.save(transfer);
                            ticketTransferRepository.save(new TicketTransfer(req.getTimestamp(), qr, transfer));
                            return true;
                        }
                    }
                    else{
                        throw new Exception("Ticket transfer reserved for another user");
                    }
                }
                else{
                    throw new Exception("Ticket already received");
                }
            }
            Integer toVerify = (int) qr.getCreationTime().getTime();
            Integer toCompare = (int) req.getTimestamp().getTime();
            if (toCompare - toVerify > 600000 || toCompare - toVerify < 0) {
                transfer.setResult(false);
                transfer.setReceiver(null);
                transferService.save(transfer);
                return false;
            }
            else{
                transfer.setResult(true);
                transfer.setReceiver(userService.findById(req.getReceiverId()));
                transferService.save(transfer);
                ticketTransferRepository.save(new TicketTransfer(req.getTimestamp(), qr, transfer));
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean delete(UUID id) throws Exception {
        try {
            TicketTransfer ticketTransfer = ticketTransferRepository.findById(id).orElse(null);
            if (ticketTransfer == null) {
                throw new Exception("TicketTransfer not found");
            }
            ticketTransferRepository.delete(ticketTransfer);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<TicketTransfer> findAll() {
        return ticketTransferRepository.findAll();
    }

    @Override
    public TicketTransfer findById(UUID id) {
        return ticketTransferRepository.findById(id).orElse(null);
    }

}
