package com.bancrabs.villaticket.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancrabs.villaticket.models.dtos.SaveLocationDTO;
import com.bancrabs.villaticket.models.entities.Location;
import com.bancrabs.villaticket.repositories.LocationRepository;
import com.bancrabs.villaticket.services.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Boolean saveLocation(SaveLocationDTO data) throws Exception {
        try {
            Location check = locationRepository.findByIdOrName(data.getId(), data.getName());
            if (check == null) {
                locationRepository.save(new Location(data.getId(), data.getName(), data.getIsAvailable()));
            } else {
                check.setId(data.getId());
                check.setName(data.getName());
                check.setIsAvailable(data.getIsAvailable());
                locationRepository.save(check);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean deleteLocation(String id) throws Exception {
        try {
            Location toDelete = locationRepository.findByIdOrName(id, id);
            if (toDelete == null) {
                throw new Exception("Location not found");
            }
            locationRepository.delete(toDelete);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean setAvailable(String id) throws Exception {
        try{
            Location toUpdate = locationRepository.findByIdOrName(id, id);
            if (toUpdate == null) {
                throw new Exception("Location not found");
            }
            toUpdate.setIsAvailable(true);
            locationRepository.save(toUpdate);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean setUnavailable(String id) throws Exception {
        try{
            Location toUpdate = locationRepository.findByIdOrName(id, id);
            if (toUpdate == null) {
                throw new Exception("Location not found");
            }
            toUpdate.setIsAvailable(false);
            locationRepository.save(toUpdate);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Location findByIdOrName(String id) {
        return locationRepository.findByIdOrName(id, id);
    }

}
