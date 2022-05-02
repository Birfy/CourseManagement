package com.example.coursemanagement.service;

import com.example.coursemanagement.common.CourseStatus;
import com.example.coursemanagement.model.*;
import com.example.coursemanagement.repository.CourseRepository;
import com.example.coursemanagement.repository.DepartmentRepository;
import com.example.coursemanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public record StudentService(StudentRepository studentRepository,
                             DepartmentRepository departmentRepository,
                             CourseRepository courseRepository) {

    public List<Course> getAvailableCourses(long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(RuntimeException::new);


        List<Course> allCourses = courseRepository.findAll();
        List<Course> result = new ArrayList<>();

        Set<TimeSlot> studentTimeSlots = new HashSet<>();
        Set<CourseStudent> courseStudents = student.getCourses();
        Set<Course> courses = new HashSet<>();
        for (CourseStudent cs : courseStudents) {
            Course c = cs.getCourse();
            courses.add(c);

            if (cs.getCourseStatus() == CourseStatus.ENROLLED)

                studentTimeSlots.addAll(c.getTimeslots());

        }
//        System.out.println(studentTimeSlots);

        for (Course c : allCourses) {
            if (!courses.contains(c)) {
                boolean conflict = false;
                for (TimeSlot t : c.getTimeslots()) {
                    if (studentTimeSlots.contains(t)) {
                        conflict = true;
                        break;
                    }
                }

                if (!conflict) {
                    result.add(c);
                }
            }

        }

        return result;
    }


    public List<Student> addStudents(List<Student> studentList) {

        return studentRepository.saveAll(studentList);

    }


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public Student getStudentById(long id) {
        return studentRepository.findById(id).orElseThrow(
                RuntimeException::new);
    }


    public Student updateStudent(Student student, long id) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(
                RuntimeException::new);


        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setDepartment(student.getDepartment());
//        existingStudent.setLastName(student.getLastName());

        studentRepository.save(existingStudent);
        return existingStudent;
    }

    public Student deleteStudent(long id) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(
                RuntimeException::new);

        studentRepository.deleteById(id);
        return existingStudent;
    }

    public Student assignDepartmentById(long studentId, long departmentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                RuntimeException::new);
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                RuntimeException::new);

        student.setDepartment(department);
        return studentRepository.save(student);
    }
}
