package com.example.coursemanagement.service;

import com.example.coursemanagement.model.Department;
import com.example.coursemanagement.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        super();
        this.departmentRepository = departmentRepository;
    }


    public List<Department> addDepartments(List<Department> DepartmentList) {

        return departmentRepository.saveAll(DepartmentList);

    }


    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }


    public Department getDepartmentById(long id) {
        return departmentRepository.findById(id).orElseThrow(
                RuntimeException::new);
    }


    public Department updateDepartment(Department department, long id) {
        Department existingDepartment = departmentRepository.findById(id).orElseThrow(
                RuntimeException::new);


        existingDepartment.setName(department.getName());

        departmentRepository.save(existingDepartment);
        return existingDepartment;
    }

    public Department deleteDepartment(long id) {
        Department existingDepartment = departmentRepository.findById(id).orElseThrow(
                RuntimeException::new);

        departmentRepository.deleteById(id);
        return existingDepartment;
    }
}