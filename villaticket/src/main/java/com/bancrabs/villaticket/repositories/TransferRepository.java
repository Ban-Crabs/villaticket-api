package com.bancrabs.villaticket.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.Transfer;

public interface TransferRepository extends ListCrudRepository<Transfer, UUID>{
    List<Transfer> findBySenderId(UUID senderId);
    List<Transfer> findByReceiverId(UUID receiverId);
    List<Transfer> findBySenderIdAndReceiverId(UUID senderId, UUID receiverId);
    List<Transfer> findByResultIsNotNull();
    List<Transfer> findByResultIsNull();
    List<Transfer> findByResultIsTrue();
    List<Transfer> findByResultIsFalse();
}
