package com.example.proiect.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(){
        super("The employee doesn't exist");
    }
}
