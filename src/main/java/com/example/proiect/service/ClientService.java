package com.example.proiect.service;

import com.example.proiect.exception.BankNotFoundException;
import com.example.proiect.exception.ClientNotFoundException;
import com.example.proiect.exception.EmployeeNotFoundException;
import com.example.proiect.model.Bank;
import com.example.proiect.model.Client;
import com.example.proiect.model.Employee;
import com.example.proiect.repository.BankRepository;
import com.example.proiect.repository.ClientRepository;
import com.example.proiect.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final BankRepository bankRepository;
    private final EmployeeRepository employeeRepository;

    public ClientService(ClientRepository clientRepository, BankRepository bankRepository, EmployeeRepository employeeRepository) {
        this.clientRepository = clientRepository;
        this.bankRepository = bankRepository;
        this.employeeRepository = employeeRepository;
    }

    public Client saveClient(Client client, int employeeId, int bankId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new);
        Bank bank = bankRepository.findById(bankId).orElseThrow(BankNotFoundException::new);

        client.setEmployee(employee);
        client.setBanks(List.of(bank));

        return clientRepository.save(client);
    }

    public List<Client> retrieveClients() {
        return clientRepository.findAll();
    }

    public List<Client> retrieveClientsByAccounts(String currency) {
        return clientRepository.findClientsByAccountCurrency(currency);
    }

    public List<Client> retrieveClientsByEmployeeName(String employeeName) {
        return clientRepository.findClientsByEmployeeName(employeeName);
    }

    public boolean deleteClientById(int clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        clientRepository.deleteById(clientId);
        return true;
    }
}
