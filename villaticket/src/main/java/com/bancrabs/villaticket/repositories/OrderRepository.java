package com.bancrabs.villaticket.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.Order;

public interface OrderRepository extends ListCrudRepository<Order, UUID>{
    List<Order> findByUserId(UUID userId);
    List<Order> findByPurchaseDate(Date date);
    List<Order> findByPurchaseMethod(String method);
}
