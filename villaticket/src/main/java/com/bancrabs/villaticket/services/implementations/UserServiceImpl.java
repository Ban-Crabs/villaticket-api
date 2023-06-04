package com.bancrabs.villaticket.services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bancrabs.villaticket.models.dtos.LoginDTO;
import com.bancrabs.villaticket.models.dtos.save.SaveUserDTO;
import com.bancrabs.villaticket.models.entities.User;
import com.bancrabs.villaticket.repositories.UserRepository;
import com.bancrabs.villaticket.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean register(SaveUserDTO data) throws Exception {
        try{
            User check = userRepository.findByUsernameOrEmail(data.getUsername(), data.getEmail());
            if(check == null){
                userRepository.save(new User(data.getUsername(), data.getEmail(), passwordEncoder.encode(data.getPassword())));
                return true;
            }
            else{
                throw new Exception("User already exists");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean deleteById(String id) throws Exception{
        try{
            User toDelete = userRepository.findByUsernameOrEmail(id, id);
            if(toDelete == null){
                throw new Exception("User not found");
            }
            userRepository.delete(toDelete);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
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

    @Override
    public Boolean login(LoginDTO data) throws Exception {
        try{
            User check = userRepository.findByUsernameOrEmail(data.getId(), data.getId());
            if(check == null){
                throw new Exception("User not found");
            }
            else{
                if(passwordEncoder.matches(data.getPassword(), check.getPassword())){
                    return true;
                }
                else{
                    throw new Exception("Wrong password");
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean update(SaveUserDTO data, String id) throws Exception {
        try{
            User toUpdate = userRepository.findByUsernameOrEmail(id, id);
            if(toUpdate == null){
                throw new Exception("User not found");
            }
            else{
                toUpdate.setUsername(data.getUsername());
                toUpdate.setEmail(data.getEmail());
                toUpdate.setPassword(passwordEncoder.encode(data.getPassword()));
                userRepository.save(toUpdate);
                return true;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
