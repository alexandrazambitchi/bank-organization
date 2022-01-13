package com.example.proiect.service;

import com.example.proiect.exception.BankNotFoundException;
import com.example.proiect.model.Bank;
import com.example.proiect.model.Employee;
import com.example.proiect.repository.BankRepository;
import com.example.proiect.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final BankRepository bankRepository;

    public EmployeeService(EmployeeRepository employeeRepository, BankRepository bankRepository) {
        this.employeeRepository = employeeRepository;
        this.bankRepository = bankRepository;
    }

    public Employee saveEmployee(Employee employee, int bankId) {
        Bank bank = bankRepository.findById(bankId).orElseThrow(BankNotFoundException::new);

        employee.setBank(bank);

        return employeeRepository.save(employee);
    }

    public List<Employee> retrieveEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> retrieveEmployeesManagingFirms(String clientType) {
        return employeeRepository.findEmployeesManagingFirms(clientType);
    }
}
