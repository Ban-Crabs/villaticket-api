package com.bancrabs.villaticket.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;

import com.bancrabs.villaticket.models.dtos.SaveLocationDTO;
import com.bancrabs.villaticket.models.entities.Location;
import com.bancrabs.villaticket.repositories.LocationRepository;
import com.bancrabs.villaticket.services.LocationService;

public class LocationServiceImpl implements LocationService{

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Boolean saveLocation(SaveLocationDTO data) throws Exception {
        try{
            Location check = locationRepository.findByIdOrName(data.getId(), data.getName());
            if(check == null){
                check = new Location(data.getId(), data.getName(), data.getIsAvailable());
                locationRepository.save(check);
            }else{
                check.setId(data.getId());
                check.setName(data.getName());
                check.setIsAvailable(data.getIsAvailable());
                locationRepository.save(check);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean deleteLocation(String id) throws Exception {
        Location toDelete = locationRepository.findByIdOrName(id, id);
        if(toDelete == null){
            return false;
        }
        locationRepository.delete(toDelete);
        return true;
    }

    @Override
    public Boolean updateLocation(SaveLocationDTO data) throws Exception {
        Location check = locationRepository.findByIdOrName(data.getId(), data.getName());
        if(check == null){
            return false;
        }
        check.setId(data.getId());
        check.setName(data.getName());
        check.setIsAvailable(data.getIsAvailable());
        locationRepository.save(check);
        return true;
    }

    @Override
    public Boolean setAvailable(String id) throws Exception {
        Location toUpdate = locationRepository.findByIdOrName(id, id);
        if(toUpdate == null){
            return false;
        }
        toUpdate.setIsAvailable(true);
        locationRepository.save(toUpdate);
        return true;
    }

    @Override
    public Boolean setUnavailable(String id) throws Exception {
        Location toUpdate = locationRepository.findByIdOrName(id, id);
        if(toUpdate == null){
            return false;
        }
        toUpdate.setIsAvailable(false);
        locationRepository.save(toUpdate);
        return true;
    }

    @Override
    public Location findByIdOrName(String id) {
        return locationRepository.findByIdOrName(id, id);
    }
    
}
