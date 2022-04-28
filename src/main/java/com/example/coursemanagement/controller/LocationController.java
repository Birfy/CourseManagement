package com.example.coursemanagement.controller;

import com.example.coursemanagement.model.Location;
import com.example.coursemanagement.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        super();
        this.locationService = locationService;
    }

    @PostMapping()
    public ResponseEntity<List<Location>> saveLocation(@RequestBody List<Location> locations) {
        return new ResponseEntity<>(locationService.addLocations(locations), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Location>> getAllLocation() {
        return new ResponseEntity<>(locationService.getAllLocations(), HttpStatus.OK);
    }

    @GetMapping({"{id}"})
    public ResponseEntity<Location> getLocations(@PathVariable("id") long locationId) {
//        System.out.println(LocationId);
        return new ResponseEntity<>(locationService.getLocationById(locationId), HttpStatus.OK);
    }

    @PutMapping({"{id}"})
    public ResponseEntity<Location> updateLocation(@RequestBody Location location,
                                                  @PathVariable("id") long locationId) {
        return new ResponseEntity<>(locationService.updateLocation(location, locationId), HttpStatus.OK);
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<String> deleteLocation(@PathVariable("id") long locationId) {
        locationService.deleteLocation(locationId);
        return new ResponseEntity<>("Employee deleted successfully!", HttpStatus.OK);
    }
}
