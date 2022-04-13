package com.example.coursemanagement.service;

import com.example.coursemanagement.model.Department;
import com.example.coursemanagement.model.Student;
import com.example.coursemanagement.repository.DepartmentRepository;
import com.example.coursemanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public StudentService(StudentRepository studentRepository, DepartmentRepository departmentRepository) {
        super();
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
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
