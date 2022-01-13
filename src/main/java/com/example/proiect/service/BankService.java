package com.example.proiect.service;

import com.example.proiect.exception.BankNotFoundException;
import com.example.proiect.exception.ClientNotFoundException;
import com.example.proiect.exception.EmployeeNotFoundException;
import com.example.proiect.model.*;
import com.example.proiect.repository.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BankService {
    private final AccountRepository accountRepository;
    private final BankDetailsRepository bankDetailsRepository;
    private final BankRepository bankRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final PaymentRepository paymentRepository;

    public BankService(AccountRepository accountRepository, BankDetailsRepository bankDetailsRepository, BankRepository bankRepository, ClientRepository clientRepository, EmployeeRepository employeeRepository, PaymentRepository paymentRepository) {
        this.accountRepository = accountRepository;
        this.bankDetailsRepository = bankDetailsRepository;
        this.bankRepository = bankRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.paymentRepository = paymentRepository;
    }

    public BankDetails saveNewBankDetails(BankDetails bankDetails) {
        return bankDetailsRepository.save(bankDetails);
    }

    public Bank saveNewBank(Bank bank, int bankDetailsId) {
        BankDetails bankDetails = bankDetailsRepository.findById(bankDetailsId).orElseThrow(() -> new RuntimeException("Id not valid!!"));

        bank.setBankDetails(bankDetails);
        return bankRepository.save(bank);
    }

    public Employee saveNewEmployee(Employee employee, int bankId) {
        Bank bank = bankRepository.findById(bankId).orElseThrow(BankNotFoundException::new);

        employee.setBank(bank);

        return employeeRepository.save(employee);
    }

    public Client saveNewClient(Client client, int employeeId, int bankId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new);
        Bank bank = bankRepository.findById(bankId).orElseThrow(BankNotFoundException::new);

        client.setEmployee(employee);
        client.setBanks(Arrays.asList(bank));

        return clientRepository.save(client);
    }

    public Account saveNewAccount(Account account, int clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);

        account.setClient(client);

        return accountRepository.save(account);
    }

    public Payment saveNewPayment(Payment payment, int clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);

        payment.setClient(client);

        return paymentRepository.save(payment);
    }

    public List<Bank> retrieveBanks() {
        return bankRepository.findAll();
    }

    public List<Employee> retrieveEmployees() {
        return employeeRepository.findAll();
    }

    public List<Client> retrieveClients() {
        return clientRepository.findAll();
    }

    public List<Client> retrieveClientsByAccounts(String currency) {
        return clientRepository.findClientsByAccountCurrency(currency);
    }

    public List<Account> retrieveAccountsByCurrency(String currency) {
        return accountRepository.findAccountByCurrency(currency);
    }

    public List<Client> retrieveClientsByEmployeeName(String employeeName) {
        return clientRepository.findClientsByEmployeeName(employeeName);
    }

    public List<Employee> retrieveEmployeesManagingFirms(String clientType) {
        return employeeRepository.findEmployeesManagingFirms(clientType);
    }

    public List<Client> retrieveClientsPayments(Integer value) {
        return clientRepository.findClientsByPayments(value);
    }

    public List<Bank> retrieveBanksByCity(String city) {
        return bankRepository.findBanksByCity(city);
    }


}
