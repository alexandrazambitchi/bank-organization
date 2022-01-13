package com.example.proiect.repository;

import com.example.proiect.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("select e from Employee e join Client c on c.employee.employeeId = e.employeeId where c.clientType = ?1 ")
    List<Employee> findEmployeesManagingFirms(String clientType);
}
