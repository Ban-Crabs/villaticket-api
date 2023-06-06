package com.bancrabs.villaticket.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bancrabs.villaticket.models.dtos.LoginDTO;
import com.bancrabs.villaticket.models.dtos.response.UserResponseDTO;
import com.bancrabs.villaticket.models.dtos.save.RecordAttendanceDTO;
import com.bancrabs.villaticket.models.dtos.save.SavePrivilegeDTO;
import com.bancrabs.villaticket.models.dtos.save.SaveUserDTO;
import com.bancrabs.villaticket.models.entities.User;
import com.bancrabs.villaticket.services.AttendanceService;
import com.bancrabs.villaticket.services.UserPrivilegeService;
import com.bancrabs.villaticket.services.UserService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserPrivilegeService userPrivilegeService;

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@ModelAttribute @Valid LoginDTO data, BindingResult result) {
        try{
            if(result.hasErrors()){
                return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
            }

            if(userService.login(data)){
                return new ResponseEntity<>("Token", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@ModelAttribute @Valid SaveUserDTO data, BindingResult result){
        try{
            if(result.hasErrors()){
                return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
            }

            if(userService.register(data)){
                return new ResponseEntity<>("Created", HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>("Conflict", HttpStatus.CONFLICT);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathParam("id") String id){
        try{
            if(userService.deleteById(id)){
                return new ResponseEntity<>("Deleted", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathParam("id") String id, @ModelAttribute @Valid SaveUserDTO data, BindingResult result){
        try{
            if(result.hasErrors()){
                return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
            }

            if(userService.update(data, id)){
                return new ResponseEntity<>("Updated", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathParam("id") String id){
        try{
            User user = userService.findById(id);
            if(user != null){
                return new ResponseEntity<>(new UserResponseDTO(user.getUsername(), user.getEmail()), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll(){
        try{
            List<User> rawUsers = userService.findAll();
            List<UserResponseDTO> users = new ArrayList<>();
            rawUsers.forEach(us->{
                users.add(new UserResponseDTO(us.getUsername(), us.getEmail()));
            });
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/privilege")
    public ResponseEntity<?> addPrivilege(@RequestParam("userId") String id, @RequestParam("privName") String privName){
        try{
            User user = userService.findById(id);
            if(user == null){
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }

            if(userPrivilegeService.save(new SavePrivilegeDTO(privName, user.getId()))){
                return new ResponseEntity<>("Created", HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>("Conflict", HttpStatus.CONFLICT);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/privilege")
    public ResponseEntity<?> getPrivileges(@PathParam("id") String id){
        try{
            return new ResponseEntity<>(userPrivilegeService.findByUserId(id), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/privilege")
    public ResponseEntity<?> getAllPrivileges(){
        try{
            return new ResponseEntity<>(userPrivilegeService.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/attendance")
    public ResponseEntity<?> attendEvent(@RequestParam("userId") String id, @RequestParam("eventId") UUID eventId){
        try{
            if(attendanceService.save(new RecordAttendanceDTO(id, eventId))){
                return new ResponseEntity<>("Created", HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>("Conflict", HttpStatus.CONFLICT);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/attendance")
    public ResponseEntity<?> getAttendance(@PathParam("id") String id){
        try{
            User user = userService.findById(id);
            if(user == null){
                return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(attendanceService.findByUserId(user.getId()), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/attendance")
    public ResponseEntity<?> getAllAttendance(){
        try{
            return new ResponseEntity<>(attendanceService.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
