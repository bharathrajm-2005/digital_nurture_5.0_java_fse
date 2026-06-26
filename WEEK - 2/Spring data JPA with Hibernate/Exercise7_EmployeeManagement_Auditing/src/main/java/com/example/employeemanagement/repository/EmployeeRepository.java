package com.example.employeemanagement.repository;

import com.example.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import com.example.employeemanagement.dto.EmployeeProjection;
import com.example.employeemanagement.dto.EmployeeDto;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartmentId(Long departmentId);
    
    @Query("SELECT e FROM Employee e WHERE e.department.name = :deptName")
    List<Employee> findByDepartmentName(@Param("deptName") String deptName);
    
    Employee findByEmailNamed(@Param("email") String email);

    // Exercise 6: Pagination and Sorting is built into JpaRepository via findAll(Pageable)
    Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Exercise 8: Projections
    List<EmployeeProjection> findProjectedByDepartmentId(Long departmentId);
    
    @Query("SELECT new com.example.employeemanagement.dto.EmployeeDto(e.name, e.email) FROM Employee e")
    List<EmployeeDto> findAllDto();
}
