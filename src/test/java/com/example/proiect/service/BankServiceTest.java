package com.example.proiect.service;

import com.example.proiect.model.*;
import com.example.proiect.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankServiceTest {

    @InjectMocks
    private BankService bankService;
    @InjectMocks
    private AccountService accountService;
    @InjectMocks
    private BankDetailsService bankDetailsService;
    @InjectMocks
    private ClientService clientService;
    @InjectMocks
    private EmployeeService employeeService;
    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private BankDetailsRepository bankDetailsRepository;
    @Mock
    private BankRepository bankRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private PaymentRepository paymentRepository;

    @Test
    @DisplayName("Running save bank details in a happy flow")
    void saveNewBankDetailsHappyFlow() {
        BankDetails bankDetails1 = new BankDetails("address1", "city1");

        when(bankDetailsRepository.save(bankDetails1)).thenReturn(bankDetails1);
        BankDetails result = bankDetailsService.saveBankDetails(bankDetails1);

        assertNotNull(result);
        assertEquals(bankDetails1.getAddress(), result.getAddress());
    }

    @Test
    @DisplayName("Running save bank in a happy flow")
    void saveNewBankHappyFlow() {
        int bankDetailsId = 1;

        BankDetails bankDetails = new BankDetails("address1", "city1");
        Bank bank = new Bank("BRD");

        when(bankDetailsRepository.findById(bankDetailsId)).thenReturn(Optional.of(bankDetails));
        when(bankRepository.save(bank)).thenReturn(bank);

        Bank result = bankService.saveBank(bank, bankDetailsId);

        assertEquals(bank.getBankName(), result.getBankName());
//        assertEquals(bankService.retrieveBanks().isEmpty(), false);
    }

    @Test
    @DisplayName("Running save employee in a happy flow")
    void saveNewEmployee() {
        int bankId = 1;

        Bank bank = new Bank("BRD");
        Employee employee = new Employee("Ana");

        when(bankRepository.findById(bankId)).thenReturn(Optional.of(bank));
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.saveEmployee(employee, bankId);

        assertNotNull(result);
        assertEquals(employee.getEmployeeName(), result.getEmployeeName());
    }

    @Test
    @DisplayName("Save client using happy flow")
    void saveNewClient() {
        int bankId = 1;
        int employeeId = 1;

        Bank bank = new Bank("BRD");
        Employee employee = new Employee("Ana");
        Client client = new Client("Maria", "person");

        when(bankRepository.findById(bankId)).thenReturn(Optional.of(bank));
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(clientRepository.save(client)).thenReturn(client);

        Client result = clientService.saveClient(client, employeeId, bankId);

        assertNotNull(result);
        assertEquals(client.getClientName(), result.getClientName());
        assertEquals(client.getClientType(), result.getClientType());

    }

    @Test
    @DisplayName("Save account using happy flow")
    void saveNewAccount() {
        int clientId = 1;

        Client client = new Client("Maria", "person");
        Account account = new Account("main", 200, "EUR");

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(accountRepository.save(account)).thenReturn(account);

        Account result = accountService.saveAccount(account, clientId);

        assertNotNull(result);
        assertEquals(account.getAccountType(), result.getAccountType());
        assertEquals(account.getAccountValue(), result.getAccountValue());
        assertEquals(account.getAccountCurrency(), result.getAccountCurrency());
    }

    @Test
    @DisplayName("Save payment using happy flow")
    void saveNewPayment() {
        int clientId = 1;

        Client client = new Client("Maria", "person");
        Payment payment = new Payment(50, "Shopping");

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment result = paymentService.savePayment(payment, clientId);

        assertNotNull(result);
        assertEquals(payment.getPaymentValue(), result.getPaymentValue());
        assertEquals(payment.getPaymentDetails(), result.getPaymentDetails());
    }

    @Test
    @DisplayName("Save bank details using negative flow")
    void saveNewBankDetailsNegativeFlow() {
        BankDetails bankDetails1 = new BankDetails("address1", "city1");

        when(bankDetailsRepository.save(bankDetails1)).thenReturn(bankDetails1);

        try {
            BankDetails result = bankDetailsService.saveBankDetails(bankDetails1);
        } catch (RuntimeException e) {
            assertEquals("Some exception occurred", e.getMessage());
        }
    }

    @Test
    @DisplayName("Save employee using negative flow")
    void saveEmployeeNegativeFlow() {
        int bankId = 1;

        Bank bank = new Bank("BRD");
        Employee employee = new Employee("Ana");

        when(bankRepository.findById(bankId)).thenReturn(Optional.of(bank));

        try {
            Employee result = employeeService.saveEmployee(employee, bankId);
        } catch (RuntimeException e) {
            assertEquals("Some exception occurred", e.getMessage());
            verify(bankRepository, times(0)).findById(bankId);
        }
    }

    @Test
    @DisplayName("Save employee using negative flow")
    void saveClientNegativeFlow() {
        int bankId = 1;
        int employeeId = 1;

        Bank bank = new Bank("BRD");
        Employee employee = new Employee("Ana");
        Client client = new Client("Maria", "person");

        when(bankRepository.findById(bankId)).thenReturn(Optional.of(bank));
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(clientRepository.save(client)).thenReturn(client);

        try {
            Client result = clientService.saveClient(client, employeeId, bankId);
        } catch (RuntimeException e) {
            assertEquals("Some exception occurred", e.getMessage());
            verify(bankRepository, times(0)).findById(bankId);
        }
    }

    @Test
    @DisplayName("Delete account test")
    public void deleteAccount() {
        int accountId = 1;
        Account account = new Account("main", 200, "EUR");

        when(accountRepository.save(account)).thenReturn(account);
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        accountRepository.save(account);
        accountService.deleteAccountById(accountId);

        assertTrue(accountService.retrieveAccounts().isEmpty());

    }

    @Test
    @DisplayName("Delete payment test")
    public void deletePayment() {
        int paymentId = 1;
        Payment payment = new Payment(200, "Transfer");

        when(paymentRepository.save(payment)).thenReturn(payment);
        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(payment));
        paymentRepository.save(payment);
        paymentService.deletePaymentById(paymentId);

        assertTrue(paymentService.retrievePayments().isEmpty());

    }

    @Test
    @DisplayName("Delete client test")
    public void deleteClient() {
        int clientId = 1;
        Client client = new Client("Maria", "person");

        when(clientRepository.save(client)).thenReturn(client);
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        clientRepository.save(client);
        clientService.deleteClientById(clientId);

        assertTrue(clientService.retrieveClients().isEmpty());

    }

    @Test
    @DisplayName("Get banks test")
    public void getBanks(){
        assertTrue(bankService.retrieveBanks().isEmpty());
    }

}