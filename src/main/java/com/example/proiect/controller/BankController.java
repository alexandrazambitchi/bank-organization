package com.example.proiect.controller;

import com.example.proiect.model.*;
import com.example.proiect.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/bankDetails/new")
    public ResponseEntity<BankDetails> saveBankDetails(@RequestBody BankDetails bankDetails) {
        return ResponseEntity.ok().body(bankService.saveNewBankDetails(bankDetails));
    }

    @PostMapping("/bank/new")
    public ResponseEntity<Bank> saveBank(@RequestBody Bank bank, @RequestParam int bankDetailsId) {
        return ResponseEntity.ok().body(bankService.saveNewBank(bank, bankDetailsId));
    }

    @PostMapping("/employee/new")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee, @RequestParam int bankId) {
        return ResponseEntity.ok().body(bankService.saveNewEmployee(employee, bankId));
    }

    @PostMapping("/client/new")
    public ResponseEntity<Client> saveClient(@RequestBody Client client, @RequestParam int employeeId, @RequestParam int bankId) {
        return ResponseEntity.ok().body(bankService.saveNewClient(client, employeeId, bankId));
    }

    @PostMapping("/account/new")
    public ResponseEntity<Account> saveAccount(@RequestBody Account account, @RequestParam int clientId) {
        return ResponseEntity.ok().body(bankService.saveNewAccount(account, clientId));
    }

    @PostMapping("/payment/new")
    public ResponseEntity<Payment> savePayment(@RequestBody Payment payment, @RequestParam int clientId){
        return ResponseEntity.ok().body(bankService.saveNewPayment(payment, clientId));
    }

    @GetMapping("/bankList")
    public ResponseEntity<List<Bank>> retrieveBanks() {
        return ResponseEntity.ok().body(bankService.retrieveBanks());
    }

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> retrieveEmployees() {
        return ResponseEntity.ok().body(bankService.retrieveEmployees());
    }

    @GetMapping("/client")
    public ResponseEntity<List<Client>> retrieveClients() {
        return ResponseEntity.ok().body(bankService.retrieveClients());
    }
}