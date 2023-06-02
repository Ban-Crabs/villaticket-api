package com.bancrabs.villaticket.services;

import java.util.List;
import java.util.UUID;

import com.bancrabs.villaticket.models.dtos.SaveUserDTO;
import com.bancrabs.villaticket.models.entities.User;

public interface UserService {
    Boolean saveUser(SaveUserDTO data);
    Boolean deleteById(UUID id);
    Boolean deleteById(String id);
    User findById(UUID id);
    User findById(String id);
    List<User> findAll();
}