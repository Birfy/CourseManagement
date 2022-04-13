package com.example.coursemanagement.controller;


import com.example.coursemanagement.model.Course;
import com.example.coursemanagement.model.CourseStudent;
import com.example.coursemanagement.model.Student;
import com.example.coursemanagement.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    @PostMapping()
    public ResponseEntity<List<Student>> saveStudent(@RequestBody List<Student> students) {
        return new ResponseEntity<>(studentService.addStudents(students), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Student>> getAllStudent() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping({"{id}"})
    public ResponseEntity<Student> getStudents(@PathVariable("id") long studentId) {
//        System.out.println(studentId);
        return new ResponseEntity<>(studentService.getStudentById(studentId), HttpStatus.OK);
    }

    @PutMapping({"{id}"})
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,
                                                  @PathVariable("id") long studentId) {
        return new ResponseEntity<>(studentService.updateStudent(student, studentId), HttpStatus.OK);
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<String> deleteStudent(@PathVariable("id") long studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>("Employee deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<Set<CourseStudent>> getCourses(@PathVariable("id") long studentId) {
        Student student = studentService.getStudentById(studentId);

        return new ResponseEntity<>(student.getCourses(), HttpStatus.OK);
    }

    @PutMapping("{studentId}/department/{departmentId}")
    public ResponseEntity<Student> assignDepartment(@PathVariable("studentId") long studentId,
                                                   @PathVariable("departmentId") long departmentId) {
        return new ResponseEntity<>(studentService.assignDepartmentById(studentId, departmentId), HttpStatus.OK);
    }
}
