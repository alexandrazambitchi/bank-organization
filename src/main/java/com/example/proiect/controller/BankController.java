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
        return ResponseEntity.ok().body(bankService.saveBankDetails(bankDetails));
    }

    @PostMapping("/bank/new")
    public ResponseEntity<Bank> saveBank(@RequestBody Bank bank, @RequestParam int bankDetailsId) {
        return ResponseEntity.ok().body(bankService.saveBank(bank, bankDetailsId));
    }

    @PostMapping("/employee/new")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee, @RequestParam int bankId) {
        return ResponseEntity.ok().body(bankService.saveEmployee(employee, bankId));
    }

    @PostMapping("/client/new")
    public ResponseEntity<Client> saveClient(@RequestBody Client client, @RequestParam int employeeId, @RequestParam int bankId) {
        return ResponseEntity.ok().body(bankService.saveClient(client, employeeId, bankId));
    }

    @PostMapping("/account/new")
    public ResponseEntity<Account> saveAccount(@RequestBody Account account, @RequestParam int clientId) {
        return ResponseEntity.ok().body(bankService.saveAccount(account, clientId));
    }

    @PostMapping("/payment/new")
    public ResponseEntity<Payment> savePayment(@RequestBody Payment payment, @RequestParam int clientId){
        return ResponseEntity.ok().body(bankService.savePayment(payment, clientId));
    }

    @GetMapping("/bankList")
    public ResponseEntity<List<Bank>> retrieveBanks() {
        return ResponseEntity.ok().body(bankService.retrieveBanks());
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> retrieveEmployees() {
        return ResponseEntity.ok().body(bankService.retrieveEmployees());
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> retrieveClients() {
        return ResponseEntity.ok().body(bankService.retrieveClients());
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> retrieveAccounts() {
        return ResponseEntity.ok().body(bankService.retrieveAccounts());
    }

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> retrievePayments() {
        return ResponseEntity.ok().body(bankService.retrievePayments());
    }

    @GetMapping("/clientsByEmployee")
    public ResponseEntity<List<Client>> retrieveClientsByEmployeeName(@RequestParam String employeeName) {
        return ResponseEntity.ok().body(bankService.retrieveClientsByEmployeeName(employeeName));
    }

    @GetMapping("/accountsInCurrency")
    public ResponseEntity<List<Account>> retrieveAccountsByCurrency(@RequestParam String currency){
        return ResponseEntity.ok().body(bankService.retrieveAccountsByCurrency(currency));
    }

    @GetMapping("/clientsByAccountsInCurrency")
    public ResponseEntity<List<Client>> retrieveClientsByAccounts(@RequestParam String currency){
        return ResponseEntity.ok().body(bankService.retrieveClientsByAccounts(currency));
    }

    @GetMapping("/employeesManagingFirms")
    public ResponseEntity<List<Employee>> retrieveEmployeesManagingFirms(@RequestParam String clientType){
        return ResponseEntity.ok().body(bankService.retrieveEmployeesManagingFirms(clientType));
    }

    @GetMapping("/clientsPaymentsBig")
    public ResponseEntity<List<Client>> retrieveClientsPayments(@RequestParam Integer value){
        return ResponseEntity.ok().body(bankService.retrieveClientsPayments(value));
    }

    @GetMapping("/banksInCity")
    public ResponseEntity<List<Bank>> retrieveBanksInCity(@RequestParam String city)
    {
        return ResponseEntity.ok().body(bankService.retrieveBanksByCity(city));
    }

    @DeleteMapping("/client/{clientId}")
    public ResponseEntity<Boolean> deleteClientById(@PathVariable int clientId){
        return ResponseEntity.ok().body(bankService.deleteClientById(clientId));
    }

    @DeleteMapping("/account/{accountId}")
    public ResponseEntity<Boolean> deleteAccountById(@PathVariable int accountId){
        return ResponseEntity.ok().body(bankService.deleteAccountById(accountId));
    }

    @DeleteMapping("/payment/{paymentId}")
    public ResponseEntity<Boolean> deletePaymentById(@PathVariable int paymentId){
        return ResponseEntity.ok().body(bankService.deletePaymentById(paymentId));
    }

    @PutMapping("/bankDetails/update")
    public ResponseEntity<BankDetails> updateBankDetails(@RequestBody BankDetails bankDetails) {
        return ResponseEntity.ok().body(bankService.saveBankDetails(bankDetails));
    }

    @PutMapping("/bank/update")
    public ResponseEntity<Bank> updateBank(@RequestBody Bank bank, @RequestParam int bankDetailsId) {
        return ResponseEntity.ok().body(bankService.saveBank(bank, bankDetailsId));
    }

    @PutMapping("/employee/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @RequestParam int bankId) {
        return ResponseEntity.ok().body(bankService.saveEmployee(employee, bankId));
    }

    @PutMapping("/client/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client, @RequestParam int employeeId, @RequestParam int bankId) {
        return ResponseEntity.ok().body(bankService.saveClient(client, employeeId, bankId));
    }

    @PutMapping("/account/update")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account, @RequestParam int clientId) {
        return ResponseEntity.ok().body(bankService.saveAccount(account, clientId));
    }

    @PutMapping("/payment/update")
    public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment, @RequestParam int clientId){
        return ResponseEntity.ok().body(bankService.savePayment(payment, clientId));
    }
}
