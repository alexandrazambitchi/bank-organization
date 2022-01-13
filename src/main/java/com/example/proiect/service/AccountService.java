package com.example.proiect.service;

import com.example.proiect.exception.ClientNotFoundException;
import com.example.proiect.model.Account;
import com.example.proiect.model.Client;
import com.example.proiect.repository.AccountRepository;
import com.example.proiect.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public AccountService(AccountRepository accountRepository, ClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    public Account saveAccount(Account account, int clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);

        account.setClient(client);

        return accountRepository.save(account);
    }

    public List<Account> retrieveAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> retrieveAccountsByCurrency(String currency) {
        return accountRepository.findAccountByCurrency(currency);
    }

    public boolean deleteAccountById(int accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found!!"));
        accountRepository.deleteById(accountId);
        return true;
    }
}
