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
public class CourseStudentService {
    private final CourseStudentRepository courseStudentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseStudentService(CourseStudentRepository courseStudentRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        super();
        this.courseStudentRepository = courseStudentRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public boolean isAvailable(Course course, Student student) {
        Set<TimeSlot> courseTimeSlots = new HashSet<>(course.getTimeslots());

        Set<TimeSlot> studentTimeSlots = new HashSet<>();
        Set<CourseStudent> courseStudents = student.getCourses();
        for (CourseStudent cs: courseStudents) {
            if (cs.getCourseStatus() == CourseStatus.ENROLLED) {
                Course c = cs.getCourse();
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
        if (isAvailable(course, student)) {

            return courseStudentRepository.save(courseStudent);
        }


        return courseStudent;
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
