package com.bancrabs.villaticket.services;

import java.util.List;
import java.util.UUID;

import com.bancrabs.villaticket.models.dtos.SaveUserDTO;
import com.bancrabs.villaticket.models.entities.User;

public interface UserService {
    Boolean register(SaveUserDTO data) throws Exception;
    Boolean deleteById(String id) throws Exception;
    User findById(UUID id);
    User findById(String id);
    List<User> findAll();
}