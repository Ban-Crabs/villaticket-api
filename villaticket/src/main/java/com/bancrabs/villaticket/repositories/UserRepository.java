package com.bancrabs.villaticket.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.User;

public interface UserRepository extends ListCrudRepository<User, UUID> {
    User findByUsernameOrEmail(String username, String email);
    List<User> findAllByOrderByUsernameAsc();
    List<User> findAllByOrderByUsernameDesc();
    List<User> findByEmailOrUsernameContainsAllIgnoringCase(String queryString);
}
