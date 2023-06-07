package com.bancrabs.villaticket.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.Token;
import com.bancrabs.villaticket.models.entities.User;

public interface TokenRepository 
	extends ListCrudRepository<Token, UUID>{ 
	
	List<Token> findByUserAndActive(User user, Boolean active);

}