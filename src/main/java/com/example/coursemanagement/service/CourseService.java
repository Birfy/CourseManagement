package com.example.coursemanagement.service;

import com.example.coursemanagement.model.*;
import com.example.coursemanagement.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final LocationRepository locationRepository;
    private final DepartmentRepository departmentRepository;
    private final TeacherRepository teacherRepository;
    private final TimeSlotRepository timeSlotRepository;

    public CourseService(CourseRepository courseRepository, LocationRepository locationRepository,
                         DepartmentRepository departmentRepository, TeacherRepository teacherRepository,
                         TimeSlotRepository timeSlotRepository) {
        super();
        this.courseRepository = courseRepository;
        this.locationRepository = locationRepository;
        this.departmentRepository = departmentRepository;
        this.teacherRepository = teacherRepository;
        this.timeSlotRepository = timeSlotRepository;
    }


    public List<Course> addCourses(List<Course> courseList) {

        return courseRepository.saveAll(courseList);

    }


    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }


    public Course getCourseById(long id) {
        return courseRepository.findById(id).orElseThrow(
                RuntimeException::new);
    }


    public Course updateCourse(Course course, long id) {
        Course existingCourse = courseRepository.findById(id).orElseThrow(
                RuntimeException::new);


        existingCourse.setName(course.getName());

        return courseRepository.save(existingCourse);
    }

    public Course deleteCourse(long id) {
        Course existingCourse = courseRepository.findById(id).orElseThrow(
                RuntimeException::new);

        courseRepository.deleteById(id);
        return existingCourse;
    }

    public Course assignLocationById(long courseId, long locationId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                RuntimeException::new);
        Location location = locationRepository.findById(locationId).orElseThrow(
                RuntimeException::new);

        course.setLocation(location);
        return courseRepository.save(course);
    }

    public Course assignDepartmentById(long courseId, long departmentId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                RuntimeException::new);
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                RuntimeException::new);

        course.setDepartment(department);
        return courseRepository.save(course);
    }

    public Course assignTeacherById(long courseId, long teacherId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                RuntimeException::new);
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(
                RuntimeException::new);

        course.setTeacher(teacher);
        return courseRepository.save(course);
    }

    public Course assignTimeSlotById(long courseId, long timeSlotId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                RuntimeException::new);
        TimeSlot timeSlot = timeSlotRepository.findById(timeSlotId).orElseThrow(
                RuntimeException::new);

        course.getTimeslots().add(timeSlot);
        return courseRepository.save(course);
    }
}
