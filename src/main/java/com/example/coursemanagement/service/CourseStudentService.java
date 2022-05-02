package com.example.coursemanagement.service;

import com.example.coursemanagement.common.CourseGrade;
import com.example.coursemanagement.common.CourseStatus;
import com.example.coursemanagement.model.Course;
import com.example.coursemanagement.model.CourseStudent;
import com.example.coursemanagement.model.Student;
import com.example.coursemanagement.model.TimeSlot;
import com.example.coursemanagement.repository.CourseRepository;
import com.example.coursemanagement.repository.CourseStudentRepository;
import com.example.coursemanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public record CourseStudentService(
        CourseStudentRepository courseStudentRepository,
        CourseRepository courseRepository,
        StudentRepository studentRepository) {

    public boolean isAvailable(Course course, Student student) {
        Set<TimeSlot> courseTimeSlots = course.getTimeslots();

        Set<TimeSlot> studentTimeSlots = new HashSet<>();
        Set<CourseStudent> courseStudents = student.getCourses();
        for (CourseStudent cs : courseStudents) {
            Course c = cs.getCourse();
            if (c.getId() == course.getId())
                return false;
            if (cs.getCourseStatus() == CourseStatus.ENROLLED) {
                studentTimeSlots.addAll(c.getTimeslots());
            }
        }

        courseTimeSlots.retainAll(studentTimeSlots);
        return courseTimeSlots.isEmpty();
    }


    public CourseStudent enrollById(long courseId, long studentId) {
        Course course = courseRepository.findById(courseId).orElseThrow(RuntimeException::new);
        Student student = studentRepository.findById(studentId).orElseThrow(RuntimeException::new);
        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setCourseStatus(CourseStatus.ENROLLED);
        courseStudent.setStudent(student);
        courseStudent.setCourse(course);
//        if (isAvailable(course, student)) {
//
        return courseStudentRepository.save(courseStudent);
//        }


//        return courseStudent;
    }

    public CourseStudent dropById(long courseId, long studentId) {
        CourseStudent courseStudent = courseStudentRepository.getCourseStudentById(courseId, studentId);
        courseStudent.setCourseStatus(CourseStatus.WITHDRAWN);

        return courseStudentRepository.save(courseStudent);

    }

    public CourseStudent gradeById(long courseId, long studentId, CourseGrade courseGrade) {
        CourseStudent courseStudent = courseStudentRepository.getCourseStudentById(courseId, studentId);
        courseStudent.setCourseGrade(courseGrade);
        courseStudent.setCourseStatus(CourseStatus.ENDED);

        return courseStudentRepository.save(courseStudent);

    }


    public CourseStudent updateCourseStudent(CourseStudent courseStudent, long id) {
        CourseStudent existingCourseStudent = courseStudentRepository.findById(id).orElseThrow(
                RuntimeException::new);


        existingCourseStudent.setCourseStatus(courseStudent.getCourseStatus());

        courseStudentRepository.save(existingCourseStudent);
        return existingCourseStudent;
    }
}
