package com.bancrabs.villaticket.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.bancrabs.villaticket.models.dtos.RegisterOrderDTO;
import com.bancrabs.villaticket.models.entities.Order;

public interface OrderService {
    Boolean save(RegisterOrderDTO data) throws Exception;

    List<Order> findAll();
    List<Order> findByUserId(UUID userId);
    List<Order> findByPurchaseDate(Date date);
    List<Order> findByPurchaseMethod(String method);
    Order findById(UUID id);
}
