package com.bancrabs.villaticket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancrabs.villaticket.models.dtos.save.SaveGenericDTO;
import com.bancrabs.villaticket.models.dtos.save.SaveImageDTO;
import com.bancrabs.villaticket.models.dtos.save.SaveLocationDTO;
import com.bancrabs.villaticket.models.dtos.save.SaveTypeDTO;
import com.bancrabs.villaticket.services.CategoryService;
import com.bancrabs.villaticket.services.ImageService;
import com.bancrabs.villaticket.services.LocationService;
import com.bancrabs.villaticket.services.TypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/eventaux")
public class EventAuxController {
    
    @Autowired
    private TypeService typeService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/type")
    public ResponseEntity<?> createType(@ModelAttribute @Valid SaveTypeDTO data){
        try{
            if(typeService.save(data)){
                return new ResponseEntity<>("Created", HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/location")
    public ResponseEntity<?> createLocation(@ModelAttribute @Valid SaveLocationDTO data){
        try{
            if(locationService.save(data)){
                return new ResponseEntity<>("Created", HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/image")
    public ResponseEntity<?> createImage(@ModelAttribute @Valid SaveImageDTO data){
        try{
            if(imageService.save(data)){
                return new ResponseEntity<>("Created", HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@ModelAttribute @Valid SaveGenericDTO data){
        try{
            if(categoryService.save(data)){
                return new ResponseEntity<>("Created", HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/type")
    public ResponseEntity<?> getAllTypes(){
        try{
            return new ResponseEntity<>(typeService.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/location")
    public ResponseEntity<?> getAllLocations(){
        try{
            return new ResponseEntity<>(locationService.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/image")
    public ResponseEntity<?> getAllImages(){
        try{
            return new ResponseEntity<>(imageService.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category")
    public ResponseEntity<?> getAllCategories(){
        try{
            return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
