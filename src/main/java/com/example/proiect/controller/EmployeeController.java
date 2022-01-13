package com.example.proiect.controller;

import com.example.proiect.model.Employee;
import com.example.proiect.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/new")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee, @RequestParam int bankId) {
        return ResponseEntity.ok().body(employeeService.saveEmployee(employee, bankId));
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> retrieveEmployees() {
        return ResponseEntity.ok().body(employeeService.retrieveEmployees());
    }

    @GetMapping("/employeesManagingFirms")
    public ResponseEntity<List<Employee>> retrieveEmployeesManagingFirms(@RequestParam String clientType){
        return ResponseEntity.ok().body(employeeService.retrieveEmployeesManagingFirms(clientType));
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @RequestParam int bankId) {
        return ResponseEntity.ok().body(employeeService.saveEmployee(employee, bankId));
    }

}
