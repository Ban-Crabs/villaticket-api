package com.bancrabs.villaticket.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.User;

public interface UserRepository extends ListCrudRepository<User, UUID> {
    User findByEmailOrUsername(String identifier);
    List<User> findAllByOrderByUsernameAsc();
    List<User> findAllByOrderByUsernameDesc();
    List<User> findByEmailOrUsernameContainsAllIgnoringCase(String queryString);
}
