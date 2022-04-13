package com.example.coursemanagement.controller;

import com.example.coursemanagement.model.TimeSlot;
import com.example.coursemanagement.service.TimeSlotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timeslots")
public class TimeSlotController {
    private TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        super();
        this.timeSlotService = timeSlotService;
    }

    @PostMapping()
    public ResponseEntity<List<TimeSlot>> saveTimeSlot(@RequestBody List<TimeSlot> timeSlots) {
        return new ResponseEntity<>(timeSlotService.addTimeSlots(timeSlots), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<TimeSlot>> getAllTimeSlot() {
        return new ResponseEntity<>(timeSlotService.getAllTimeSlots(), HttpStatus.OK);
    }

    @GetMapping({"{id}"})
    public ResponseEntity<TimeSlot> getTimeSlots(@PathVariable("id") long timeSlotId) {
//        System.out.println(TimeSlotId);
        return new ResponseEntity<>(timeSlotService.getTimeSlotById(timeSlotId), HttpStatus.OK);
    }

//    @PutMapping({"{id}"})
//    public ResponseEntity<TimeSlot> updateTimeSlot(@RequestBody TimeSlot timeSlot,
//                                                   @PathVariable("id") long timeSlotId) {
//        return new ResponseEntity<>(timeSlotService.updateTimeSlot(timeSlot, timeSlotId), HttpStatus.OK);
//    }

//    @DeleteMapping({"{id}"})
//    public ResponseEntity<String> deleteTimeSlot(@PathVariable("id") long TimeSlotId) {
//        TimeSlotService.deleteTimeSlot(TimeSlotId);
//        return new ResponseEntity<>("Employee deleted successfully!", HttpStatus.OK);
//    }
}
