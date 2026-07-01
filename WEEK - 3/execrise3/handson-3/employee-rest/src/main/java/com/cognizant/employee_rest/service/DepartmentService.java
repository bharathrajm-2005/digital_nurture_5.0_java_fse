package com.cognizant.employee_rest.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.employee_rest.dao.DepartmentDao;
import com.cognizant.employee_rest.model.Department;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    public ArrayList<Department> getAllDepartments() {

        return departmentDao.getAllDepartments();

    }

}