package com.example.coursemanagement.service;

import com.example.coursemanagement.model.Department;
import com.example.coursemanagement.model.Teacher;
import com.example.coursemanagement.repository.DepartmentRepository;
import com.example.coursemanagement.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record TeacherService(TeacherRepository teacherRepository,
                             DepartmentRepository departmentRepository) {


    public List<Teacher> addTeachers(List<Teacher> teacherList) {

        return teacherRepository.saveAll(teacherList);

    }


    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }


    public Teacher getTeacherById(long id) {
        return teacherRepository.findById(id).orElseThrow(
                RuntimeException::new);
    }


    public Teacher updateTeacher(Teacher teacher, long id) {
        Teacher existingTeacher = teacherRepository.findById(id).orElseThrow(
                RuntimeException::new);


        existingTeacher.setFirstName(teacher.getFirstName());
        existingTeacher.setLastName(teacher.getLastName());
        existingTeacher.setEmail(teacher.getEmail());
//        existingTeacher.setLastName(Teacher.getLastName());

        teacherRepository.save(existingTeacher);
        return existingTeacher;
    }

    public Teacher deleteTeacher(long id) {
        Teacher existingTeacher = teacherRepository.findById(id).orElseThrow(
                RuntimeException::new);

        teacherRepository.deleteById(id);
        return existingTeacher;
    }

    public Teacher assignDepartmentById(long teacherId, long departmentId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(
                RuntimeException::new);
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                RuntimeException::new);

        teacher.setDepartment(department);
        return teacherRepository.save(teacher);
    }
}
