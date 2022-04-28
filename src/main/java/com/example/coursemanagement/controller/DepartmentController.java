package com.example.coursemanagement.controller;

import com.example.coursemanagement.model.Department;
import com.example.coursemanagement.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        super();
        this.departmentService = departmentService;
    }

    @PostMapping()
    public ResponseEntity<List<Department>> saveDepartment(@RequestBody List<Department> departments) {
        return new ResponseEntity<>(departmentService.addDepartments(departments), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Department>> getAllDepartment() {
        return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping({"{id}"})
    public ResponseEntity<Department> getDepartments(@PathVariable("id") long departmentId) {
//        System.out.println(DepartmentId);
        return new ResponseEntity<>(departmentService.getDepartmentById(departmentId), HttpStatus.OK);
    }

    @PutMapping({"{id}"})
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department,
                                                  @PathVariable("id") long departmentId) {
        return new ResponseEntity<>(departmentService.updateDepartment(department, departmentId), HttpStatus.OK);
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") long departmentId) {
        departmentService.deleteDepartment(departmentId);
        return new ResponseEntity<>("Employee deleted successfully!", HttpStatus.OK);
    }
}
