package com.bancrabs.villaticket.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.bancrabs.villaticket.models.entities.UserPrivilege;

public interface UserPrivilegeRepository extends ListCrudRepository<UserPrivilege, UUID>{
    List<UserPrivilege> findByUserId(String userId);
    List<UserPrivilege> findByNameByOrderByIdAsc(String name);
    List<UserPrivilege> findByNameByOrderByIdDesc(String name);
}