package com.bancrabs.villaticket.services;

import java.util.List;
import java.util.UUID;

import com.bancrabs.villaticket.models.dtos.LoginDTO;
import com.bancrabs.villaticket.models.dtos.save.SaveUserDTO;
import com.bancrabs.villaticket.models.entities.Token;
import com.bancrabs.villaticket.models.entities.User;

public interface UserService {
    Boolean register(SaveUserDTO data) throws Exception;
    Boolean login(LoginDTO data) throws Exception;
    Boolean update(SaveUserDTO data, String id) throws Exception;
    Boolean deleteById(String id) throws Exception;
    User findById(UUID id);
    User findById(String id);
    List<User> findAll();

    Token registerToken(User user) throws Exception;
	Boolean isTokenValid(User user, String token);
	void cleanTokens(User user) throws Exception;
    User findUserAuthenticated();
}