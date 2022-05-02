package com.example.coursemanagement.service;

import com.example.coursemanagement.model.Location;
import com.example.coursemanagement.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record LocationService(LocationRepository locationRepository) {


    public List<Location> addLocations(List<Location> locationList) {

        return locationRepository.saveAll(locationList);

    }


    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }


    public Location getLocationById(long id) {
        return locationRepository.findById(id).orElseThrow(
                RuntimeException::new);
    }


    public Location updateLocation(Location Location, long id) {
        Location existingLocation = locationRepository.findById(id).orElseThrow(
                RuntimeException::new);


        existingLocation.setBuilding(Location.getBuilding());
        existingLocation.setRoom(Location.getRoom());
//        existingLocation.setEmail(Location.getEmail());
//        existingLocation.setLastName(Location.getLastName());

        locationRepository.save(existingLocation);
        return existingLocation;
    }

    public Location deleteLocation(long id) {
        Location existingLocation = locationRepository.findById(id).orElseThrow(
                RuntimeException::new);

        locationRepository.deleteById(id);
        return existingLocation;
    }
}
