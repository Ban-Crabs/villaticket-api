package com.bancrabs.villaticket.services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancrabs.villaticket.models.dtos.SavePrivilegeDTO;
import com.bancrabs.villaticket.models.entities.User;
import com.bancrabs.villaticket.models.entities.UserPrivilege;
import com.bancrabs.villaticket.repositories.UserPrivilegeRepository;
import com.bancrabs.villaticket.services.UserPrivilegeService;
import com.bancrabs.villaticket.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserPrivilegeServiceImpl implements UserPrivilegeService{

    @Autowired
    private UserPrivilegeRepository userPrivilegeRepository;

    @Autowired
    private UserService userService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean save(SavePrivilegeDTO data) throws Exception {
        try{
            User related = userService.findById(data.getUserId());
            if(related == null){
                throw new Exception("User not found");
            }
            UserPrivilege check = userPrivilegeRepository.findByNameAndUserId(data.getName(), data.getUserId());
            if(check == null){
                userPrivilegeRepository.save(new UserPrivilege(data.getName(), related));
            }
            else{
                check.setName(data.getName());
                check.setUser(related);
                userPrivilegeRepository.save(check);
            }
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean delete(SavePrivilegeDTO data) throws Exception {
        try{
            UserPrivilege check = userPrivilegeRepository.findByNameAndUserId(data.getName(), data.getUserId());
            if(check != null){
                userPrivilegeRepository.delete(check);
                return true;
            }
            else{
                throw new Exception("Privilege not found");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<UserPrivilege> findAll() {
        return userPrivilegeRepository.findAll();
    }

    @Override
    public List<UserPrivilege> findByUserId(UUID userId) {
        return userPrivilegeRepository.findByUserId(userId);
    }

    @Override
    public List<UserPrivilege> findByName(String name) {
        return userPrivilegeRepository.findByName(name);
    }

    @Override
    public UserPrivilege findById(UUID id) {
        return userPrivilegeRepository.findById(id).orElse(null);
    }
    
}
