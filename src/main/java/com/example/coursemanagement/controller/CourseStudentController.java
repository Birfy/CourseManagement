package com.example.coursemanagement.controller;


import com.example.coursemanagement.common.CourseGrade;
import com.example.coursemanagement.model.CourseStudent;
import com.example.coursemanagement.service.CourseStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/course_student")
public class CourseStudentController {

    private final CourseStudentService courseStudentService;

    public CourseStudentController(CourseStudentService courseStudentService) {
        this.courseStudentService = courseStudentService;

    }

    @PostMapping("/enroll/{courseId}/{studentId}")
    public ResponseEntity<CourseStudent> enrollStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        return new ResponseEntity<>(courseStudentService.enrollById(courseId, studentId), HttpStatus.OK);
    }

    @PutMapping("/drop/{courseId}/{studentId}")
    public ResponseEntity<CourseStudent> dropStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        return new ResponseEntity<>(courseStudentService.dropById(courseId, studentId), HttpStatus.OK);
    }

    @PutMapping("/grade/{courseId}/{studentId}/{grade}")
    public ResponseEntity<CourseStudent> gradeStudentForCourse(@PathVariable Long courseId, @PathVariable Long studentId, @PathVariable CourseGrade grade) {
        return new ResponseEntity<>(courseStudentService.gradeById(courseId, studentId, grade), HttpStatus.OK);
    }
}
