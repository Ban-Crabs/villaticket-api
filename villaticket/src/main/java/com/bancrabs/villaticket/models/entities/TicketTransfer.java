package com.bancrabs.villaticket.models.entities;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ticket_transfer")
public class TicketTransfer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "transfer_time", nullable = false)
    private Timestamp transferTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "qr_id", nullable = true)
    private QR qr;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transfer_id", nullable = true)
    private Transfer transfer;

    TicketTransfer(Timestamp transferTime, QR qr, Transfer transfer) {
        this.transferTime = transferTime;
        this.qr = qr;
        this.transfer = transfer;
    }
}
