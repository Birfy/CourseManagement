package com.example.coursemanagement.controller;

import com.example.coursemanagement.model.Course;
import com.example.coursemanagement.model.CourseStudent;
import com.example.coursemanagement.model.Student;
import com.example.coursemanagement.service.CourseService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping()
    public ResponseEntity<List<Course>> addCourse(@RequestBody List<Course> courses) {
        return new ResponseEntity<>(courseService.addCourses(courses), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Course>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") long courseId) {
//        System.out.println(CourseId);
        return new ResponseEntity<>(courseService.getCourseById(courseId), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course,
                                                   @PathVariable("id") long courseId) {
        return new ResponseEntity<>(courseService.updateCourse(course, courseId), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable("id") long courseId) {

        return new ResponseEntity<>(courseService.deleteCourse(courseId), HttpStatus.OK);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Set<CourseStudent>> getCoursesByStudent(@PathVariable("id") long studentId) {
        Course course = courseService.getCourseById(studentId);

        return new ResponseEntity<>(course.getStudents(), HttpStatus.OK);
    }

    @PutMapping("{courseId}/location/{locationId}")
    public ResponseEntity<Course> assignLocation(@PathVariable("courseId") long courseId,
                                                 @PathVariable("locationId") long locationId) {
        return new ResponseEntity<>(courseService.assignLocationById(courseId, locationId), HttpStatus.OK);
    }

    @PutMapping("{courseId}/department/{departmentId}")
    public ResponseEntity<Course> assignDepartment(@PathVariable("courseId") long courseId,
                                                   @PathVariable("departmentId") long departmentId) {
        return new ResponseEntity<>(courseService.assignDepartmentById(courseId, departmentId), HttpStatus.OK);
    }

    @PutMapping("{courseId}/teacher/{teacherId}")
    public ResponseEntity<Course> assignTeacher(@PathVariable("courseId") long courseId,
                                                @PathVariable("teacherId") long teacherId) {
        return new ResponseEntity<>(courseService.assignTeacherById(courseId, teacherId), HttpStatus.OK);
    }

    @PutMapping("{courseId}/timeslot/{timeSlotId}")
    public ResponseEntity<Course> assignTimeSlot(@PathVariable("courseId") long courseId,
                                                @PathVariable("timeSlotId") long timeSlotId) {
        return new ResponseEntity<>(courseService.assignTimeSlotById(courseId, timeSlotId), HttpStatus.OK);
    }


}
