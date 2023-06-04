package com.bancrabs.villaticket.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.UserPrivilege;

public interface UserPrivilegeRepository extends ListCrudRepository<UserPrivilege, UUID>{
    UserPrivilege findByNameAndUserId(String name, UUID userId);
    List<UserPrivilege> findByUserId(UUID userId);
    List<UserPrivilege> findByName(String name);
    List<UserPrivilege> findByNameByOrderByIdAsc(String name);
    List<UserPrivilege> findByNameByOrderByIdDesc(String name);
}