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
    void saveNewBankDetailsHappyFlow(){
        BankDetails bankDetails1 = new BankDetails("address1", "city1");

        when(bankDetailsRepository.save(bankDetails1)).thenReturn(bankDetails1);
        BankDetails result = bankService.saveBankDetails(bankDetails1);

        assertNotNull(result);
        assertEquals(bankDetails1.getAddress(), result.getAddress());
    }

    @Test
    @DisplayName("Running save bank in a happy flow")
    void saveNewBankHappyFlow(){
        int bankDetailsId = 1;

        BankDetails bankDetails = new BankDetails("address1", "city1");
        Bank bank = new Bank("BRD");

        when(bankDetailsRepository.findById(bankDetailsId)).thenReturn(Optional.of(bankDetails));
        when(bankRepository.save(bank)).thenReturn(bank);

        Bank result = bankService.saveBank(bank, bankDetailsId);

        assertEquals(bank.getBankName(), result.getBankName());
    }

    @Test
    @DisplayName("Running save employee in a happy flow")
    void saveNewEmployee(){
        int bankId = 1;

        Bank bank = new Bank("BRD");
        Employee employee = new Employee("Ana");

        when(bankRepository.findById(bankId)).thenReturn(Optional.of(bank));
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = bankService.saveEmployee(employee, bankId);

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

        Client result = bankService.saveClient(client, employeeId, bankId);

        assertNotNull(result);
        assertEquals(client.getClientName(), result.getClientName());
        assertEquals(client.getClientType(), result.getClientType());

    }

    @Test
    @DisplayName("Save account using happy flow")
    void saveNewAccount(){
        int clientId = 1;

        Client client = new Client("Maria", "person");
        Account account = new Account("main", 200, "EUR");

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(accountRepository.save(account)).thenReturn(account);

        Account result = bankService.saveAccount(account, clientId);

        assertNotNull(result);
        assertEquals(account.getAccountType(), result.getAccountType());
        assertEquals(account.getAccountValue(), result.getAccountValue());
        assertEquals(account.getAccountCurrency(), result.getAccountCurrency());
    }

    @Test
    @DisplayName("Save payment using happy flow")
    void saveNewPayment(){
        int clientId = 1;

        Client client = new Client("Maria", "person");
        Payment payment = new Payment(50, "Shopping");

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment result = bankService.savePayment(payment, clientId);

        assertNotNull(result);
        assertEquals(payment.getPaymentValue(), result.getPaymentValue());
        assertEquals(payment.getPaymentDetails(), result.getPaymentDetails());
    }

    @Test
    @DisplayName("Save employee using negative flow")
    void saveEmployeeNegativeFlow() {
        int bankId = 1;

        Bank bank = new Bank("BRD");
        Employee employee = new Employee("Ana");

        when(bankRepository.findById(bankId)).thenReturn(Optional.of(bank));

        try{
            Employee result = bankService.saveEmployee(employee, bankId);
        }catch (RuntimeException e) {
            assertEquals("Some exception occured", e.getMessage());
            verify(bankRepository, times(0)).findById(bankId);
        }
    }

}