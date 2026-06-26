package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentRepository repository;

    @GetMapping
    public List<Department> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Department create(@RequestBody Department dept) {
        return repository.save(dept);
    }
}
