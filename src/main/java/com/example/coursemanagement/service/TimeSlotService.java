package com.example.coursemanagement.service;

import com.example.coursemanagement.model.TimeSlot;
import com.example.coursemanagement.repository.TimeSlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record TimeSlotService(TimeSlotRepository timeSlotRepository) {


    public List<TimeSlot> addTimeSlots(List<TimeSlot> timeSlotList) {

        return timeSlotRepository.saveAll(timeSlotList);

    }


    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotRepository.findAll();
    }


    public TimeSlot getTimeSlotById(long id) {
        return timeSlotRepository.findById(id).orElseThrow(
                RuntimeException::new);
    }


//    public TimeSlot updateTimeSlot(TimeSlot timeSlot, long id) {
//        TimeSlot existingTimeSlot = timeSlotRepository.findById(id).orElseThrow(
//                RuntimeException::new);
//
//
//        existingTimeSlot.setBuilding(TimeSlot.getBuilding());
//        existingTimeSlot.setRoom(TimeSlot.getRoom());
////        existingTimeSlot.setEmail(TimeSlot.getEmail());
////        existingTimeSlot.setLastName(TimeSlot.getLastName());
//
//        TimeSlotRepository.save(existingTimeSlot);
//        return existingTimeSlot;
//    }

//    public TimeSlot deleteTimeSlot(long id) {
//        TimeSlot existingTimeSlot = TimeSlotRepository.findById(id).orElseThrow(
//                RuntimeException::new);
//
//        TimeSlotRepository.deleteById(id);
//        return existingTimeSlot;
//    }
}
