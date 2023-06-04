package com.bancrabs.villaticket.services.implementations;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancrabs.villaticket.models.dtos.RegisterOrderDTO;
import com.bancrabs.villaticket.models.entities.Order;
import com.bancrabs.villaticket.models.entities.User;
import com.bancrabs.villaticket.repositories.OrderRepository;
import com.bancrabs.villaticket.services.OrderService;
import com.bancrabs.villaticket.services.UserService;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    //TODO: Ask if the order date should be a timestamp or a date
    //TODO: Ask if the user/date combination should be unique
    //TODO: Ask if the order should have a 'valid' field

    @Override
    public Boolean save(RegisterOrderDTO data) throws Exception {
        try{
            User user = userService.findById(data.getUserId());
            if(user == null){
                throw new Exception("User not found");
            }
            orderRepository.save(new Order(data.getPurchaseDate(), data.getPurchaseMethod(), user));
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByUserId(UUID userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> findByPurchaseDate(Date date) {
        return orderRepository.findByPurchaseDate(date);
    }

    @Override
    public List<Order> findByPurchaseMethod(String method) {
        return orderRepository.findByPurchaseMethod(method);
    }

    @Override
    public Order findById(UUID id) {
        return orderRepository.findById(id).orElse(null);
    }
    
}
