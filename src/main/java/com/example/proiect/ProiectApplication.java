package com.example.proiect;

import com.example.proiect.model.*;
import com.example.proiect.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ProiectApplication implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BankDetailsRepository bankDetailsRepository;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProiectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Bank bank1 = new Bank("BCR");
        Bank bank2 = new Bank("ING");

        bankRepository.save(bank1);
        bankRepository.save(bank2);

        BankDetails bankDetails1 = new BankDetails("Road 1", "Bucharest");
        BankDetails bankDetails2 = new BankDetails("Road 2", "Bucharest");

        bankDetailsRepository.save(bankDetails1);
        bankDetailsRepository.save(bankDetails2);

        Employee employee1 = new Employee("John");
        Employee employee2 = new Employee("Jane");
        Employee employee3 = new Employee("Mark");

        bank1.setBankDetails(bankDetails1);
        bank2.setBankDetails(bankDetails2);

        bank1.setEmployeeList(Arrays.asList(employee1, employee2));
        bank2.setEmployeeList(Arrays.asList(employee3));

        bankRepository.save(bank1);
        bankRepository.save(bank2);

        employee1.setBank(bank1);
        employee2.setBank(bank1);
        employee3.setBank(bank2);

        Client client1 = new Client("Anna", "person");
        Client client2 = new Client("Apple", "firm");
        Client client3 = new Client("Maria", "person");
        Client client4 = new Client("George", "person");

        bankRepository.save(bank1);
        bankRepository.save(bank2);

//        bank1.setClientList(Arrays.asList(client1, client2, client3));
//        bank2.setClientList(Arrays.asList(client4));

//        bankRepository.save(bank2);
//        bankRepository.save(bank1);

        client1.setBanks(Arrays.asList(bank1));
        client2.setBanks(Arrays.asList(bank1));
        client3.setBanks(Arrays.asList(bank1));
        client4.setBanks(Arrays.asList(bank2));

        employee1.setClientList(Arrays.asList(client1, client2));
        employee2.setClientList(Arrays.asList(client3));
        employee3.setClientList(Arrays.asList(client4));

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

        client1.setEmployee(employee1);
        client2.setEmployee(employee1);
        client3.setEmployee(employee2);
        client4.setEmployee(employee3);

        Account account1 = new Account("main", 100, "EUR");
        Account account2 = new Account("savings", 1000, "EUR");
        Account account3 = new Account("main", 500, "EUR");
        Account account4 = new Account("main", 130, "USD");
        Account account5 = new Account("main", 150, "EUR");

        client1.setAccountList(Arrays.asList(account1, account2));
        client2.setAccountList(Arrays.asList(account3));
        client3.setAccountList(Arrays.asList(account4));
        client4.setAccountList(Arrays.asList(account5));

        clientRepository.save(client1);
        clientRepository.save(client2);
        clientRepository.save(client3);
        clientRepository.save(client4);

        account1.setClient(client1);
        account2.setClient(client1);
        account3.setClient(client2);
        account4.setClient(client3);
        account5.setClient(client4);

        Payment payment1 = new Payment(20, "Online Order");
        Payment payment2 = new Payment(10, "Food shop");
        Payment payment3 = new Payment(5, "Subscription");
        Payment payment4 = new Payment(15, "Online Order");

        payment1.setClient(client1);
        payment2.setClient(client2);
        payment3.setClient(client3);
        payment4.setClient(client4);

        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);
        accountRepository.save(account4);
        accountRepository.save(account5);

        paymentRepository.save(payment1);
        paymentRepository.save(payment2);
        paymentRepository.save(payment3);
        paymentRepository.save(payment4);

        client1.setPaymentList(Arrays.asList(payment1));
        client2.setPaymentList(Arrays.asList(payment2));
        client3.setPaymentList(Arrays.asList(payment3));
        client4.setPaymentList(Arrays.asList(payment4));

        clientRepository.save(client1);
        clientRepository.save(client2);
        clientRepository.save(client3);
        clientRepository.save(client4);
    }
}
