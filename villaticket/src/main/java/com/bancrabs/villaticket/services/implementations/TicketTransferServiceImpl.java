package com.bancrabs.villaticket.services.implementations;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancrabs.villaticket.models.dtos.SaveTicketTransferDTO;
import com.bancrabs.villaticket.models.entities.QR;
import com.bancrabs.villaticket.models.entities.TicketTransfer;
import com.bancrabs.villaticket.models.entities.Transfer;
import com.bancrabs.villaticket.repositories.TicketTransferRepository;
import com.bancrabs.villaticket.services.QRService;
import com.bancrabs.villaticket.services.TicketTransferService;
import com.bancrabs.villaticket.services.TransferService;

import jakarta.transaction.Transactional;

@Service
public class TicketTransferServiceImpl implements TicketTransferService {

    @Autowired
    private TicketTransferRepository ticketTransferRepository;

    @Autowired
    private TransferService transferService;

    @Autowired
    private QRService qrService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean verify(Timestamp timestamp, SaveTicketTransferDTO data) throws Exception {
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
                throw new Exception("TicketTransfer already exists");
            }
            Integer toVerify = (int) qr.getCreationTime().getTime();
            Integer toCompare = (int) timestamp.getTime();
            if (toCompare - toVerify > 600000 || toCompare - toVerify < 0) {
                throw new Exception("QR expired");
            }
            transfer.setResult(true);
            transferService.save(transfer);
            ticketTransferRepository.save(new TicketTransfer(timestamp, qr, transfer));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean delete(UUID id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
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
