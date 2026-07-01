package com.cognizant.employee_rest.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.employee_rest.dao.EmployeeDao;
import com.cognizant.employee_rest.model.Employee;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public ArrayList<Employee> getAllEmployees() {

        return employeeDao.getAllEmployees();

    }

}