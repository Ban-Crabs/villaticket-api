package com.bancrabs.villaticket.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.QR;

public interface QRRepository extends ListCrudRepository<QR, UUID>{
    
}
