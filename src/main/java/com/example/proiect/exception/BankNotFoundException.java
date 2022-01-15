package com.example.proiect.exception;

public class BankNotFoundException extends RuntimeException {
    public BankNotFoundException() {
        super("The bank doesn't exist.");
    }
}
