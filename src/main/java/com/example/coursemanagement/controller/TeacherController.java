package com.example.coursemanagement.controller;

import com.example.coursemanagement.model.Teacher;
import com.example.coursemanagement.model.Teacher;
import com.example.coursemanagement.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/teachers")


public class TeacherController {

    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        super();
        this.teacherService = teacherService;
    }

    @PostMapping()
    public ResponseEntity<List<Teacher>> saveTeacher(@RequestBody List<Teacher> teachers) {
        return new ResponseEntity<>(teacherService.addTeachers(teachers), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Teacher>> getAllTeacher() {
        return new ResponseEntity<>(teacherService.getAllTeachers(), HttpStatus.OK);
    }

    @GetMapping({"{id}"})
    public ResponseEntity<Teacher> getTeachers(@PathVariable("id") long teacherId) {
//        System.out.println(teacherId);
        return new ResponseEntity<>(teacherService.getTeacherById(teacherId), HttpStatus.OK);
    }

    @PutMapping({"{id}"})
    public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher teacher,
                                                  @PathVariable("id") long teacherId) {
        return new ResponseEntity<>(teacherService.updateTeacher(teacher, teacherId), HttpStatus.OK);
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<String> deleteTeacher(@PathVariable("id") long teacherId) {
        teacherService.deleteTeacher(teacherId);
        return new ResponseEntity<>("Employee deleted successfully!", HttpStatus.OK);
    }

    @PutMapping("{teacherId}/department/{departmentId}")
    public ResponseEntity<Teacher> assignDepartment(@PathVariable("teacherId") long TeacherId,
                                                    @PathVariable("departmentId") long departmentId) {
        return new ResponseEntity<>(teacherService.assignDepartmentById(TeacherId, departmentId), HttpStatus.OK);
    }
}
