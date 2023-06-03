package com.bancrabs.villaticket.services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancrabs.villaticket.models.dtos.SaveUserDTO;
import com.bancrabs.villaticket.models.entities.User;
import com.bancrabs.villaticket.repositories.UserRepository;
import com.bancrabs.villaticket.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean register(SaveUserDTO data) throws Exception {
        try{
            User check = userRepository.findByUsernameOrEmail(data.getUsername(), data.getEmail());
            if(check == null){
                check = new User(data.getUsername(), data.getEmail(), data.getPassword());
                userRepository.save(check);
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean deleteById(String id) throws Exception{
        try{
            User toDelete = userRepository.findByUsernameOrEmail(id, id);
            if(toDelete == null){
                return false;
            }
            userRepository.delete(toDelete);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findById(String id) {
        return userRepository.findByUsernameOrEmail(id, id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAllByOrderByUsernameAsc();
    }
    
}
